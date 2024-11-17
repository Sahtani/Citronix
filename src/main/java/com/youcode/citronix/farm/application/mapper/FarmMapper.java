package com.youcode.citronix.farm.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.domain.entity.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper extends GenericMapper<Farm,FarmRequestDTO, FarmResponseDTO> {
}
