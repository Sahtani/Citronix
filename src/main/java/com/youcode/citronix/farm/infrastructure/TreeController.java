package com.youcode.citronix.farm.infrastructure;


import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.application.service.TreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trees")
public class TreeController extends GenericController<TreeRequestDTO, TreeResponseDTO, Long> {
    public TreeController(TreeService service) {
        super(service);
    }
}
