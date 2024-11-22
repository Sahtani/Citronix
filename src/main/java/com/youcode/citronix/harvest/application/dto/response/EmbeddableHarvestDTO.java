package com.youcode.citronix.harvest.application.dto.response;

import com.youcode.citronix.harvest.domain.valueobject.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmbeddableHarvestDTO(@NotNull LocalDateTime harvestDate,
                                   @NotNull Season season) {
}
