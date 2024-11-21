package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.FieldRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FieldResponseDTO;
import com.youcode.citronix.farm.application.mapper.FieldMapper;
import com.youcode.citronix.farm.application.service.FieldService;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.entity.Field;
import com.youcode.citronix.farm.domain.exception.FieldAreaExceededException;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
import com.youcode.citronix.farm.domain.repository.FiledRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldServiceImpl extends AbstractService<Field,FieldRequestDTO, FieldResponseDTO, Long> implements FieldService {
    private final FiledRepository filedRepository;
    private final FarmRepository farmRepository;
    private final FieldMapper mapper;

    private static final double MIN_FIELD_SIZE_HECTARES = 0.1;
    private static final double MAX_FIELD_SIZE_PERCENTAGE = 0.5;
    private static final int MAX_FIELDS_COUNT = 10;
    public FieldServiceImpl(FiledRepository filedRepository, FarmRepository farmRepository, FieldMapper mapper) {
        super(filedRepository, mapper);
        this.filedRepository = filedRepository;
        this.farmRepository = farmRepository;
        this.mapper = mapper;
    }

    @Override
    public FieldResponseDTO save(FieldRequestDTO fieldRequestDTO) {
        Farm farm = farmRepository.findById(fieldRequestDTO.farmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + fieldRequestDTO.farmId() + " not found"));


        validateFieldForFarm(fieldRequestDTO, farm);

        Field field = mapper.toEntity(fieldRequestDTO);
        field.setFarm(farm).setArea(fieldRequestDTO.area());

        // Association avec la ferme
        farm.getFields().add(field);
        Field savedField = filedRepository.save(field);
        return mapper.toDto(savedField);
    }

    private void validateFieldForFarm(FieldRequestDTO fieldRequestDTO, Farm farm) {
        // Max number of fields in the farm
        final int MAX_FIELDS_COUNT = 10;

        // Min field area (in hectares)
        final double MIN_FIELD_SIZE_HECTARES = 0.1;

        // Max field area percentage of the total farm area
        final double MAX_FIELD_SIZE_PERCENTAGE = 0.5;

        // Check if the farm already has the maximum number of fields
        if (farm.getFields().size() >= MAX_FIELDS_COUNT) {
            throw new IllegalStateException("The farm can contain no more than 10 fields.");
        }

        // Check if the field area is less than the minimum required size
        if (fieldRequestDTO.area() < MIN_FIELD_SIZE_HECTARES) {
            throw new IllegalStateException("The minimum field area must be 0.1 hectare.");
        }

        // Calculate max allowed field area as a percentage of the total farm area
        double totalFarmArea = farm.getTotalArea();
        double maxFieldArea = totalFarmArea * MAX_FIELD_SIZE_PERCENTAGE;

        // Check if the field area exceeds the maximum allowed size
        if (fieldRequestDTO.area() > maxFieldArea) {
            throw new IllegalStateException("The field area cannot exceed 50% of the total farm area.");
        }

        // Calcul de la superficie totale des champs
        double totalFieldArea = farm.getFields()
                .stream()
                .mapToDouble(Field::getArea)
                .sum();

        if (totalFieldArea + fieldRequestDTO.area() > farm.getTotalArea()) {
            throw new FieldAreaExceededException("The total field area cannot exceed the farm's area. " +
                    "Requested: " + (totalFieldArea + fieldRequestDTO.area()) + ", Available: " + farm.getTotalArea());
        }

    }


}


