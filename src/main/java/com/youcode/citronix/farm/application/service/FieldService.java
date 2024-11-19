package com.youcode.citronix.farm.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.request.FieldRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FieldResponseDTO;

public interface FieldService extends GenericService<FieldRequestDTO, FieldResponseDTO, Long> {
}
