package com.youcode.citronix.common.service;

import com.youcode.citronix.farm.application.dto.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface GenericService<RequestDTO, ResponseDTO, ID> {
        ResponseDTO save(RequestDTO requestDto);

        ResponseDTO update(ID id, RequestDTO requestDto);

        ResponseDTO findById(ID id);

        PagedResponse<ResponseDTO> findAll(Pageable pageable);

        void deleteById(ID id);
    }

