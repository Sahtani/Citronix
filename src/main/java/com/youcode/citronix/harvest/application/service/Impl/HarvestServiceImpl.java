package com.youcode.citronix.harvest.application.service.Impl;


import com.youcode.citronix.common.exception.EntityNotFoundException;
import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import com.youcode.citronix.harvest.application.mapper.HarvestMapper;
import com.youcode.citronix.harvest.application.service.HarvestService;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import com.youcode.citronix.harvest.domain.valueobject.Season;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Validated
public class HarvestServiceImpl extends AbstractService<Harvest, HarvestRequestDTO, HarvestResponseDTO,Long> implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestMapper mapper;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestMapper mapper) {
        super(harvestRepository, mapper);
        this.harvestRepository = harvestRepository;
        this.mapper = mapper;
    }

    @Override
    public HarvestResponseDTO save(HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = mapper.toEntity(harvestRequestDTO);

        LocalDateTime harvestDate = harvestRequestDTO.harvestDate();
        if (harvestDate == null) {
            throw new IllegalArgumentException("Harvest date must not be null.");
        }
        Season season = Season.fromDate(LocalDate.from(harvestDate));
        harvest.setSeason(season);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return mapper.toDto(savedHarvest);
    }

    @Override
    public HarvestResponseDTO update(Long harvestId, HarvestRequestDTO harvestRequestDTO) {
        Harvest existingHarvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with ID: " + harvestId));

        LocalDateTime harvestDate = harvestRequestDTO.harvestDate();
        if (harvestDate != null) {
            existingHarvest.setHarvestDate(harvestDate);
            existingHarvest.setSeason(Season.fromDate(LocalDate.from(harvestDate)));
        }
        existingHarvest.setHarvestDate(harvestRequestDTO.harvestDate());

        Harvest updatedHarvest = harvestRepository.save(existingHarvest);

        return mapper.toDto(updatedHarvest);
    }


}
