package com.youcode.citronix.farm.application.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TreeResponseDTO(Long id,
        @NotNull LocalDateTime plantingDate,
                                 @NotNull Long fieldId) {
}
