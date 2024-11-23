package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.embeddable.FieldEmbeddableDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TreeResponseDTO(@NotNull Long id,
                              @NotNull LocalDateTime plantingDate,
                              @NotNull FieldEmbeddableDTO field,
                              double annualProductivity) {
}
