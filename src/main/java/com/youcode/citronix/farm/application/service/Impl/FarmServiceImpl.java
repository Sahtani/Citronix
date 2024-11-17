package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.mapper.FarmMapper;
import com.youcode.citronix.farm.application.service.FarmService;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class FarmServiceImpl extends AbstractService<Farm, FarmRequestDTO, FarmResponseDTO, Long> implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper mapper;

    public FarmServiceImpl( FarmRepository farmRepository, FarmMapper mapper) {
        super(farmRepository, mapper);
        this.farmRepository = farmRepository;
        this.mapper = mapper;
    }
}
