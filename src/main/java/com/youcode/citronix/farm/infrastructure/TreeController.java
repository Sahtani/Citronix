package com.youcode.citronix.farm.infrastructure;


import com.youcode.citronix.common.controller.GenericController;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.application.service.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController extends GenericController<TreeRequestDTO, TreeResponseDTO, Long> {

    private final TreeService treeService;

    public TreeController(TreeService service, TreeService treeService) {
        super(service);
        this.treeService = treeService;
    }

    @GetMapping("/{fieldId}/trees")
    public ResponseEntity<List<TreeResponseDTO>> getTreesByFieldId(@PathVariable Long fieldId) {
        List<TreeResponseDTO> trees = treeService.getTreesByFieldId(fieldId);
        return ResponseEntity.ok(trees);
    }

    @GetMapping("/{fieldId}/productivity")
    public ResponseEntity<Double> getTotalProductivityByField(@PathVariable Long fieldId) {
        double productivity = treeService.getTotalProductivityByField(fieldId);
        return ResponseEntity.ok(productivity);
    }

}
