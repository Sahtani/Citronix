package com.youcode.citronix.farm.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmCriteriaDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;

import java.util.List;

public interface FarmService extends GenericService<FarmRequestDTO, FarmResponseDTO, Long> {
     List<FarmCriteriaDTO> searchFarms(String name, String location, Double area);
}
