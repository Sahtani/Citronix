package com.youcode.citronix.farm.application.dto.Embedded;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmbeddedFarmDTO(@NotBlank String name,
                              @NotBlank String location,
                              @NotNull
                              double totalArea,
                              @NotNull
                              LocalDateTime creationDate) {
}
