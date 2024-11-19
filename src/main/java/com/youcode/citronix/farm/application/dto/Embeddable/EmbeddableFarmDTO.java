package com.youcode.citronix.farm.application.dto.Embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmbeddableFarmDTO(@NotNull Long id, @NotBlank String name,
                              @NotBlank String location,
                              @NotNull
                              double totalArea,
                              @NotNull
                              LocalDateTime creationDate) {
}
