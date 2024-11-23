package com.youcode.citronix.harvest.application.dto.response;

import com.youcode.citronix.harvest.application.dto.embeddable.HarvestDetailEmbeddableDTO;
import com.youcode.citronix.harvest.domain.valueobject.Season;
import com.youcode.citronix.sale.application.dto.embeddable.SaleEmbeddableDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record HarvestResponseDTO(@NotNull LocalDateTime harvestDate, @NotNull Season season,
                                 @NotNull Set<HarvestDetailEmbeddableDTO> harvestDetails,
                                 @NotNull Set<SaleEmbeddableDTO> sales

) {
}
