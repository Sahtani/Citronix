package com.youcode.citronix.sale.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.application.mapper.SaleMapper;
import com.youcode.citronix.sale.application.service.SaleService;
import com.youcode.citronix.sale.domain.entity.Sale;
import com.youcode.citronix.sale.domain.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SaleServiceImpl extends AbstractService<Sale, SaleRequestDTO, SaleResponseDTO,Long> implements SaleService  {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper mapper;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository, SaleMapper mapper) {
        super(saleRepository, mapper);
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
        this.mapper = mapper;
    }
}
