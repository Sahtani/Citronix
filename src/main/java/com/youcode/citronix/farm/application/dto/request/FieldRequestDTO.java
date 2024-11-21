package com.youcode.citronix.farm.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FieldRequestDTO(@NotBlank String name,
                              @NotNull double area,
                              Long farmId) {
}
