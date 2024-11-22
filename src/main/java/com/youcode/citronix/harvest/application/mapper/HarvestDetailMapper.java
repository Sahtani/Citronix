package com.youcode.citronix.harvest.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.harvest.application.dto.request.HarvestDetailRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestDetailResponseDTO;
import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper extends GenericMapper<HarvestDetail, HarvestDetailRequestDTO, HarvestDetailResponseDTO> {
}
