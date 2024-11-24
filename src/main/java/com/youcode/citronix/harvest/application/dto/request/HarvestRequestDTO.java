package com.youcode.citronix.harvest.application.dto.request;

import com.youcode.citronix.harvest.domain.valueobject.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HarvestRequestDTO(@NotNull LocalDateTime harvestDate) {
}
