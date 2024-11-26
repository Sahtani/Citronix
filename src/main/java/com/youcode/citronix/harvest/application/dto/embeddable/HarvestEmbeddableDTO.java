package com.youcode.citronix.harvest.application.dto.embeddable;

import com.youcode.citronix.harvest.domain.enums.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HarvestEmbeddableDTO(@NotNull Long id,
                                   @NotNull LocalDateTime harvestDate,
                                   @NotNull Season season,
                                   @NotNull double totalQuantity
) {
}
