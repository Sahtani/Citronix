package com.youcode.citronix.harvest.application.service.Impl;


import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.harvest.application.dto.request.HarvestDetailRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestDetailRsponseDTO;
import com.youcode.citronix.harvest.application.mapper.HarvestDetailMapper;
import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import com.youcode.citronix.harvest.domain.repository.HarvestDetailRepositroy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class HarvestDetailService extends AbstractService<HarvestDetail, HarvestDetailRequestDTO, HarvestDetailRsponseDTO,Long> implements com.youcode.citronix.harvest.application.service.HarvestDetailService {

    private final HarvestDetailRepositroy harvestDetailRepositroy;
    private final HarvestDetailMapper mapper;

    public HarvestDetailService(HarvestDetailRepositroy harvestDetailRepositroy, HarvestDetailMapper mapper) {
        super(harvestDetailRepositroy,mapper);
        this.harvestDetailRepositroy = harvestDetailRepositroy;
        this.mapper = mapper;
    }
}
