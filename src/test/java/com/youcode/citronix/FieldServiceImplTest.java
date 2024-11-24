package com.youcode.citronix;

import com.youcode.citronix.farm.application.dto.request.FieldRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FieldResponseDTO;
import com.youcode.citronix.farm.application.mapper.FieldMapper;
import com.youcode.citronix.farm.application.service.Impl.FieldServiceImpl;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.entity.Field;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
import com.youcode.citronix.farm.domain.repository.FiledRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class FieldServiceImplTest {

    @Mock
    private FiledRepository filedRepository;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldMapper fieldMapper;
    private Field field;
    private Farm farm;

    @InjectMocks
    private FieldServiceImpl fieldService;

    FieldServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        farm = new Farm(1L, "happy farm", "marrakech", 4000.0);
        field = new Field(1L, "happy field", 1400.3, farm);
    }

    @Nested
    class SaveFieldTests {

        @Test
        void shouldSaveFieldSuccessfully_WhenValidDataProvided() {
            // Arrange
            long farmId = 1L;

            Farm farm = new Farm();
            farm.setId(farmId);
            farm.setTotalArea(100.0);
            Set<Field> fields = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                fields.add(new Field());
            }
            farm.setFields(fields);

            // Configuration du champ
            FieldRequestDTO requestDTO = new FieldRequestDTO("Field 1", 10.0, farmId);
            Field field = new Field();
            field.setId(1L);
            field.setName("Field 1");
            field.setArea(10.0);
            field.setFarm(farm);

            FieldResponseDTO responseDTO = new FieldResponseDTO(1L, "Field 1", 10.0, null, null);

            // Mocks
            when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));
            when(fieldMapper.toEntity(requestDTO)).thenReturn(field);
            when(filedRepository.save(field)).thenReturn(field);
            when(fieldMapper.toDto(field)).thenReturn(responseDTO);

            // Act
            FieldResponseDTO result = fieldService.save(requestDTO);

            // Assert
            assertNotNull(result);
            assertEquals(responseDTO.id(), result.id());
            assertEquals(responseDTO.name(), result.name());
            verify(farmRepository, times(1)).findById(farmId);
            verify(filedRepository, times(1)).save(field);
        }

        @Test
        void shouldThrowException_WhenFarmNotFound() {
            // Arrange
            long farmId = 1L;
            FieldRequestDTO requestDTO = new FieldRequestDTO("Field 1", 10.0, farmId);

            when(farmRepository.findById(farmId)).thenReturn(Optional.empty());

            // Act & Assert
            EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> fieldService.save(requestDTO));
            assertEquals("Farm with ID " + farmId + " not found", exception.getMessage());
            verify(farmRepository, times(1)).findById(farmId);
        }

        @Test
        void save_ShouldThrowException_WhenFieldAreaIsTooSmall() {
            // Arrange
            long farmId = 1L;

            Farm farm = new Farm();
            farm.setId(farmId);
            farm.setTotalArea(100.0);
            Set<Field> fields = new HashSet<>();
            for (int i = 0; i < 9; i++) { // Moins de 10 champs
                fields.add(new Field());
            }
            farm.setFields(fields);

            FieldRequestDTO requestDTO = new FieldRequestDTO("Field 1", 0.05, farmId);

            when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

            // Act & Assert
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fieldService.save(requestDTO));
            assertEquals("The minimum field area must be 0.1 hectare.", exception.getMessage());

            // Vérification des interactions
            verify(farmRepository, times(1)).findById(farmId);
            verifyNoInteractions(filedRepository);
        }


        @Test
        void save_ShouldThrowException_WhenFarmHasMaxFields() {
            // Arrange
            long farmId = 1L;
            Farm farm = new Farm();
            farm.setId(farmId);
            farm.setTotalArea(100.0);

            Set<Field> fields = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                fields.add(new Field());
            }
            farm.setFields((fields));
            FieldRequestDTO requestDTO = new FieldRequestDTO("Field 1", 10.0, farmId);
            when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

            // Act & Assert
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fieldService.save(requestDTO));
            assertEquals("The farm can contain no more than 10 fields.", exception.getMessage());
            verify(farmRepository, times(1)).findById(farmId);
        }
    }
    @Nested
    class DeleteTests {
        @Test
        void givenExistentId_whenDelete_thenDeleteFarm() {
            given(filedRepository.existsById(field.getId())).willReturn(true);

            fieldService.deleteById(field.getId());

            verify(filedRepository).deleteById(field.getId());
        }

        @Test
        void givenNotExistentId_whenDelete_thenThrowEntityNotFoundException() {
            given(filedRepository.existsById(field.getId())).willReturn(false);
            assertThatExceptionOfType(EntityNotFoundException.class)
                    .isThrownBy(() -> fieldService.deleteById(field.getId()))
                    .withMessage("Entity with ID 1 not found.");

        }
    }

}


