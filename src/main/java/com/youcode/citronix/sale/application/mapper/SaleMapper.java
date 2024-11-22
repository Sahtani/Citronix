package com.youcode.citronix.sale.application.mapper;

import com.youcode.citronix.common.mapper.GenericMapper;
import com.youcode.citronix.sale.application.dto.request.SaleRequestDTO;
import com.youcode.citronix.sale.application.dto.response.SaleResponseDTO;
import com.youcode.citronix.sale.domain.entity.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper extends GenericMapper<Sale, SaleRequestDTO, SaleResponseDTO> {
}
