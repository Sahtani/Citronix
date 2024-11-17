package com.youcode.citronix.farm.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FarmRequestDTO(@NotBlank String name,
                             @NotBlank String location,
                             @NotNull
                             double totalArea,
                             @NotNull
                             LocalDateTime creationDate) {
}
