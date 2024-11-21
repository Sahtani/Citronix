package com.youcode.citronix.farm.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TreeRequestDTO(@NotNull LocalDateTime plantingDate,
                             @NotNull Long fieldId) {
}
