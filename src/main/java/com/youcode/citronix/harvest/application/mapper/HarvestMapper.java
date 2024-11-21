package com.youcode.citronix.harvest.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.harvest.application.dto.request.HarvestRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper extends GenericMapper<Harvest, HarvestRequestDTO, HarvestResponseDTO> {
}
