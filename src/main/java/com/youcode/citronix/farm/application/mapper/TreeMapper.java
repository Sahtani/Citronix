package com.youcode.citronix.farm.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.domain.entity.Tree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreeMapper extends GenericMapper<Tree, TreeRequestDTO, TreeResponseDTO> {
}
