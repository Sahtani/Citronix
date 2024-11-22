package com.youcode.citronix.sale.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.application.service.SaleService;
import com.youcode.citronix.sale.domain.entity.Sale;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SaleServiceImpl extends AbstractService<Sale, SaleRequestDTO, SaleResponseDTO,Long> implements SaleService  {
}
