package com.youcode.citronix.farm.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.farm.application.dto.request.FieldRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FieldResponseDTO;
import com.youcode.citronix.farm.application.service.FieldService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fields")
public class FieldController extends GenericController<FieldRequestDTO, FieldResponseDTO, Long> {

    public FieldController(FieldService service) {
        super(service);
    }
}
