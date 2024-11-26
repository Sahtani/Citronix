package com.youcode.citronix.sale.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.application.mapper.SaleMapper;
import com.youcode.citronix.sale.application.service.SaleService;
import com.youcode.citronix.sale.domain.entity.Sale;
import com.youcode.citronix.sale.domain.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.net.http.WebSocket;
import java.util.List;

@Service
@Validated
@Transactional
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
        Sale sale = mapper.toEntity(saleRequestDTO);
        sale.setHarvest(harvest);
        double income = sale.getIncome();
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDto(savedSale);


    }
//    public double calculateTotalRevenue(SaleRequestDTO saleRequestDTO) {
//        Harvest harvest = harvestRepository.findById(saleRequestDTO.harvestId()).orElseThrow(() -> new EntityNotFoundException("Harvest not found with ID: " + saleRequestDTO.harvestId()));
//        double income = saleRepository.findAll().stream().map(Sale::getHarvest).mapToDouble(Sale ::getIncome).sum();
//        return income ;
//    }

}
