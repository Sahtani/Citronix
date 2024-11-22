package com.youcode.citronix.harvest.application.dto.response;

import com.youcode.citronix.harvest.domain.valueobject.Season;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record HarvestResponseDTO(@NotNull LocalDateTime harvestDate, @NotNull Season season,
                                 @NotNull Set<EmbeddableHarvestDetailDTO> harvestDetails) {
}
