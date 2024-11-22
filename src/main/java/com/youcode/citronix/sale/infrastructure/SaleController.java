package com.youcode.citronix.sale.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.application.service.SaleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sales")
public class SaleController extends GenericController<SaleRequestDTO, SaleResponseDTO,Long> {
    public SaleController(SaleService service) {
        super(service);
    }
}
