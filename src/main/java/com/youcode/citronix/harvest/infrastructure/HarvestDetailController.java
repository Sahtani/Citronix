package com.youcode.citronix.harvest.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.harvest.application.dto.request.HarvestDetailRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestDetailRsponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/harvest_details")
public class HarvestDetailController extends GenericController<HarvestDetailRequestDTO, HarvestDetailRsponseDTO, Long> {
    public HarvestDetailController(GenericService<HarvestDetailRequestDTO, HarvestDetailRsponseDTO, Long> service) {
        super(service);
    }
}
