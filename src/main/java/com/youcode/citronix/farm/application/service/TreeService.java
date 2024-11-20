package com.youcode.citronix.farm.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.domain.entity.Tree;

import java.util.List;

public interface TreeService extends GenericService<TreeRequestDTO, TreeResponseDTO,Long> {

    public List<Tree> getTreesByFieldId(Long fieldId) ;
    public List<Tree> getTotalProductivityByField(Long fieldId);
}
