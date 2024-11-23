package com.youcode.citronix.farm.application.dto.embeddable;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TreeEmbeddableDTO(@NotNull Long id,
                                @NotNull LocalDateTime plantingDate) {
}
