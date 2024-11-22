package com.youcode.citronix.sale.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;

public interface SaleService extends GenericService<SaleRequestDTO, SaleResponseDTO,Long> {
}
