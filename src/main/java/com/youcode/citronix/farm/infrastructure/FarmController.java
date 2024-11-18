package com.youcode.citronix.farm.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.service.FarmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farms")
public class FarmController extends GenericController<FarmRequestDTO, FarmResponseDTO,Long> {

    public FarmController(FarmService service) {
        super(service);
    }
}
