package com.youcode.citronix.harvest.application.service;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;

public interface HarvestService extends GenericService<HarvestRequestDTO, HarvestResponseDTO,Long> {
}
