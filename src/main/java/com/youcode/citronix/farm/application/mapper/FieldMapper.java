package com.youcode.citronix.farm.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.farm.application.dto.request.FieldRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FieldResponseDTO;
import com.youcode.citronix.farm.domain.entity.Field;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper extends GenericMapper<Field, FieldRequestDTO, FieldResponseDTO> {
}
