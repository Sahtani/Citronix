package com.youcode.citronix.farm.application.dto.Embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmbeddableTreeDTO(@NotNull Long id,
        @NotNull LocalDateTime plantingDate) {
}
