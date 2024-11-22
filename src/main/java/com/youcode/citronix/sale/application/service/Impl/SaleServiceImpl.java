package com.youcode.citronix.sale.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.domain.entity.Tree;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.application.mapper.SaleMapper;
import com.youcode.citronix.sale.application.service.SaleService;
import com.youcode.citronix.sale.domain.entity.Sale;
import com.youcode.citronix.sale.domain.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
public class SaleServiceImpl extends AbstractService<Sale, SaleRequestDTO, SaleResponseDTO,Long> implements SaleService  {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper mapper;
    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository, SaleMapper mapper, SaleMapper saleMapper) {
        super(saleRepository, mapper);
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
        this.mapper = mapper;
        this.saleMapper = saleMapper;
    }
    public SaleResponseDTO save(SaleRequestDTO saleRequestDTO) {
        var harvest = harvestRepository.findById(saleRequestDTO.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with ID: " + saleRequestDTO.harvestId()));

        if (harvest.getTotalQuantity() < saleRequestDTO.quantitySold()) {
            throw new IllegalArgumentException("Requested quantity exceeds available quantity in the harvest.");
        }

        harvest.setTotalQuantity(harvest.getTotalQuantity() - saleRequestDTO.quantitySold());
        harvestRepository.save(harvest);
        Sale sale = mapper.toEntity(saleRequestDTO).setHarvest(harvest);
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);

    }

}
