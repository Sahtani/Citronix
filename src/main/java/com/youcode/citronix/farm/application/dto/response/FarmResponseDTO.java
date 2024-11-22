package com.youcode.citronix.farm.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FarmResponseDTO(@NotNull Long id ,
                              @NotBlank String name,
                              @NotBlank String location,
                              @NotNull
                              double totalArea,
                              @NotNull
                              LocalDateTime creationDate) {
}
