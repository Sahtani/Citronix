package com.youcode.citronix.farm.application.dto.embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FieldEmbeddableDTO(@NotNull Long id,
                                 @NotBlank String name,
                                 @NotNull @Positive double area) {
}
