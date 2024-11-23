package com.youcode.citronix;

import com.youcode.citronix.farm.application.dto.embeddable.FieldEmbeddableDTO;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmCriteriaDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.mapper.FarmMapper;
import com.youcode.citronix.farm.application.service.Impl.FarmServiceImpl;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FarmServiceImplTest {

    @InjectMocks
    private FarmServiceImpl farmService;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldIncludeFields_WhenFieldsArePresent() {
        // Arrange
        FarmRequestDTO requestDTO = new FarmRequestDTO("Farm B", "Location B", 5000.0, LocalDateTime.now());
        Farm farmEntity = new Farm();
        farmEntity.setName("Farm B");
        farmEntity.setLocation("Location B");
        farmEntity.setTotalArea(5000.0);

        Set<FieldEmbeddableDTO> fields = Set.of(
                new FieldEmbeddableDTO(1L, "Field 1", 2000.0),
                new FieldEmbeddableDTO(2L, "Field 2", 2500.0)
        );

        when(farmMapper.toEntity(requestDTO)).thenReturn(farmEntity);
        when(farmRepository.save(farmEntity)).thenReturn(farmEntity);
        when(farmMapper.toDto(farmEntity)).thenReturn(new FarmResponseDTO(1L, "Farm B", "Location B", 5000.0, LocalDateTime.now(), fields));

        // Act
        FarmResponseDTO response = farmService.save(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("Farm B", response.name());
        assertEquals("Location B", response.location());
        assertEquals(5000.0, response.totalArea());
        assertEquals(2, response.fields().size());
        assertTrue(response.fields().stream().anyMatch(field -> field.name().equals("Field 1")));
        assertTrue(response.fields().stream().anyMatch(field -> field.name().equals("Field 2")));
    }


    @Test
    void save_ShouldThrowException_WhenTotalAreaIsInvalid() {
        // Arrange
        FarmRequestDTO requestDTO = new FarmRequestDTO("Farm B", "Location B", 1500.0, LocalDateTime.now());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> farmService.save(requestDTO));
        assertEquals("The total area must be greater than 2000 m²", exception.getMessage());
        verify(farmRepository, never()).save(any());
    }

//    void searchFarms_ShouldReturnMatchingFarms_WhenCriteriaAreProvided() {
//        // Arrange
//        String name = "Farm A";
//        String location = "Location A";
//        Double area = 3000.0;
//
//        Farm farmEntity = new Farm();
//        farmEntity.setId(1L);
//        farmEntity.setName(name);
//        farmEntity.setLocation(location);
//        farmEntity.setTotalArea(area);
//        farmEntity.setCreationDate(LocalDateTime.now());
//
//        List<Farm> mockFarms = List.of(farmEntity);
//
//        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
//        CriteriaQuery<Farm> criteriaQuery = mock(CriteriaQuery.class);
//        Root<Farm> root = mock(Root.class);
//        TypedQuery<Farm> typedQuery = mock(TypedQuery.class);
//
//        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//        when(criteriaBuilder.createQuery(Farm.class)).thenReturn(criteriaQuery);
//        when(criteriaQuery.from(Farm.class)).thenReturn(root);
//        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
//        when(typedQuery.getResultList()).thenReturn(mockFarms);
//        when(farmMapper.toDto(farmEntity)).thenReturn(new FarmCriteriaDTO(farmEntity.getId(),farmEntity.getName(), farmEntity.getLocation(), farmEntity.getTotalArea(),farmEntity.getCreationDate()));
//
//        // Act
//        List<FarmCriteriaDTO> results = farmService.searchFarms(name, location, area);
//
//        // Assert
//        assertNotNull(results, "The result list should not be null");
//        assertFalse(results.isEmpty(), "The result list should not be empty");
//        assertEquals(1, results.size(), "The result list should contain exactly one farm");
//        assertEquals(name, results.get(0).name(), "The farm name should match the search criteria");
//        assertEquals(location, results.get(0).location(), "The farm location should match the search criteria");
//        assertEquals(area, results.get(0).totalArea(), "The farm area should match the search criteria");
//
//        // Verify that the mocked methods were called
//        verify(entityManager, times(1)).getCriteriaBuilder();
//        verify(entityManager, times(1)).createQuery(criteriaQuery);
//        verify(typedQuery, times(1)).getResultList();
//    }


    @Test
    void searchFarms_ShouldReturnEmptyList_WhenNoMatchesFound() {
        // Arrange
        when(entityManager.getCriteriaBuilder()).thenReturn(mock(jakarta.persistence.criteria.CriteriaBuilder.class));
        when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(mock(jakarta.persistence.TypedQuery.class));

        // Act
        List<FarmCriteriaDTO> results = farmService.searchFarms("NonExistent", null, null);

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
