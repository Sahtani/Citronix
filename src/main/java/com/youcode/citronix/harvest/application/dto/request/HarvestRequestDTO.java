package com.youcode.citronix.harvest.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HarvestRequestDTO(@NotNull LocalDateTime harvestDate) {
}
