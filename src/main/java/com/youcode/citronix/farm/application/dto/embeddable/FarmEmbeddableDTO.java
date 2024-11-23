package com.youcode.citronix.farm.application.dto.embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FarmEmbeddableDTO(@NotNull Long id, @NotBlank String name, @NotBlank String location, @NotNull
                              double totalArea, @NotNull
                              LocalDateTime creationDate) {
}
