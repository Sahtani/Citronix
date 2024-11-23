package com.youcode.citronix.harvest.application.dto.embeddable;

import com.youcode.citronix.harvest.domain.valueobject.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HarvestEmbeddableDTO(@NotNull LocalDateTime harvestDate,
                                   @NotNull Season season,
                                   @NotNull double totalQuantity
) {
}
