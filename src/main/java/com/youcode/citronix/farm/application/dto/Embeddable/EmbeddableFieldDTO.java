package com.youcode.citronix.farm.application.dto.Embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmbeddableFieldDTO(@NotNull Long id,
                                 @NotBlank String name,
                                 @NotNull double area) {
}
