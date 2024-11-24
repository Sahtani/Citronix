package com.youcode.citronix;

import com.youcode.citronix.common.exception.EntityNotFoundException;
import com.youcode.citronix.farm.application.dto.PagedResponse;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import com.youcode.citronix.harvest.application.mapper.HarvestMapper;
import com.youcode.citronix.harvest.application.service.HarvestService;
import com.youcode.citronix.harvest.application.service.Impl.HarvestDetailServiceImpl;
import com.youcode.citronix.harvest.application.service.Impl.HarvestServiceImpl;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import com.youcode.citronix.harvest.domain.valueobject.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class HarvestServiceUnitTest {
    @Mock
    private HarvestRepository repository;
    @Mock
    private HarvestMapper mapper;

    private Harvest harvest;
    @InjectMocks
    private HarvestServiceImpl harvestService;

    @BeforeEach
    void setup() {
        harvest = new Harvest(LocalDateTime.of(2024, 11, 21, 15, 30), Season.FALL, 28L);
    }

    @Nested
    class FindAllTests {
        @Test
        void givenHarvestsExists_whenFindAll_thenSuccess() {
            given(repository.findAll(any(PageRequest.class)))
                    .willReturn(new PageImpl<>(List.of(harvest)));
            given(mapper.toDto(harvest)).willReturn(new HarvestResponseDTO(harvest.getId(), harvest.getHarvestDate(), harvest.getSeason(), null,null));

            Pageable pageable = PageRequest.of(0, 10);
            PagedResponse<HarvestResponseDTO> actual = harvestService.findAll(pageable);
            assertThat(actual).isNotNull();
            assertThat(actual.pageSize()).isEqualTo(1);
        }

        @Test
        void givenHarvestsNotExists_whenFindAll_thenReturnEmptyList() {
            given(repository.findAll(any(PageRequest.class))).willReturn(new PageImpl<>(List.of()));
            Pageable pageable = PageRequest.of(0, 10);  // Page 0, size 10
            PagedResponse<HarvestResponseDTO> actual = harvestService.findAll(pageable);
            assertThat(actual).isNotNull();
            assertThat(actual.pageSize()).isZero();
        }
    }

    @Nested
    class FindByIdTests {
        @Test
        void givenNotExistentId_whenFindById_thenThrowEntityNotFound() {
            Long harvestId = 2L;

            given(repository.findById(harvestId)).willReturn(Optional.empty());

            // Assert that the exception is thrown
            assertThatExceptionOfType(EntityNotFoundException.class)
                    .isThrownBy(() -> harvestService.findById(harvestId))
                    .withMessage("Entity with ID 2 not found.");
        }

        @Test
        void givenExistentId_whenFindById_thenSuccess() {
            Long harvestId = 2L;
            given(repository.findById(harvestId)).willReturn(Optional.of(harvest));
            given(mapper.toDto(harvest)).willReturn(new HarvestResponseDTO(harvest.getId(), harvest.getHarvestDate(), harvest.getSeason(), null, null));

            HarvestResponseDTO actual = harvestService.findById(harvestId);

            assertThat(actual).isNotNull();
            assertThat(actual.harvestDate()).isEqualTo(harvest.getHarvestDate());
        }
    }

    @Nested
    class CreateTests {
//        @Test
//        void givenHarvestAlreadyExistsInSameSeason_whenCreate_thenThrowAlreadyExists() {
//            HarvestRequestDTO request = new HarvestRequestDTO(harvest.getHarvestDate());
//            Season season = Season.fromDate(LocalDate.from(harvest.getHarvestDate()));
//            given(repository.existsBySeason(season)).willReturn(true);
//
//            assertThatExceptionOfType(AlreadyExistsException.class)
//                    .isThrownBy(() -> harvestService.create(request))
//                    .withMessage(String.format("Already Exists A harvest in this season: %s, in date %s", season, request.date()));
//        }

//        @Test
//        void givenValidRequest_whenCreate_thenSuccess() {
//            HarvestRequestDTO request = new HarvestRequestDTO(harvest.getHarvestDate());
//            Season season = Season.fromDate(LocalDate.from(harvest.getHarvestDate()));
//            given(repository.existsBySeason(season)).willReturn(false);
//            given(repository.save(any(Harvest.class))).willReturn(harvest);
//            given(mapper.toResponseDto(harvest)).willReturn(new HarvestResponseDto(harvest.getId().value(), harvest.getDate(), harvest.getSeason(), null, null, null));
//
//            HarvestResponseDto actual = harvestService.create(request);
//
//            assertThat(actual).isNotNull();
//            assertThat(actual.season()).isEqualTo(season);
//        }
//    }
    }

}