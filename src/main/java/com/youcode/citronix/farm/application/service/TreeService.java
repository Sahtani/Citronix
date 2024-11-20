package com.youcode.citronix.farm.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;

public interface TreeService extends GenericService<TreeRequestDTO, TreeResponseDTO,Long> {
}
