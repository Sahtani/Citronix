package com.youcode.citronix.harvest.application.service.Impl;


import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import com.youcode.citronix.harvest.application.mapper.HarvestMapper;
import com.youcode.citronix.harvest.application.service.HarvestService;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import org.springframework.stereotype.Service;

@Service

public class HarvestServiceImpl extends AbstractService<Harvest, HarvestRequestDTO, HarvestResponseDTO,Long> implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestMapper mapper;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestMapper mapper) {
        super(harvestRepository, mapper);
        this.harvestRepository = harvestRepository;
        this.mapper = mapper;
    }
}
