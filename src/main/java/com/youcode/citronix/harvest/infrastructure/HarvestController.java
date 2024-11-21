package com.youcode.citronix.harvest.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController extends GenericController<HarvestRequestDTO, HarvestResponseDTO, Long> {
    public HarvestController(GenericService<HarvestRequestDTO, HarvestResponseDTO, Long> service) {
        super(service);
    }
}
