package com.youcode.citronix.farm.infrastructure;

import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.service.FarmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController extends GenericController<FarmRequestDTO, FarmResponseDTO, Long> {

    private final FarmService service;

    public FarmController(GenericService<FarmRequestDTO, FarmResponseDTO, Long> service, FarmService service1) {
        super(service);
        this.service = service1;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponseDTO>> searchFarms(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double area) {

        List<FarmResponseDTO> farms = service.searchFarms(name, location, area);

        if (farms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(farms);
    }
}
