package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.Embeddable.EmbeddableFieldDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TreeResponseDTO(Long id,
                              @NotNull LocalDateTime plantingDate,
                              @NotNull EmbeddableFieldDTO field) {
}
