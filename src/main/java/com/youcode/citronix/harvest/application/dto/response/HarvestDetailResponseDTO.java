package com.youcode.citronix.harvest.application.dto.response;

import com.youcode.citronix.farm.application.dto.embeddable.TreeEmbeddableDTO;
import com.youcode.citronix.harvest.application.dto.embeddable.HarvestEmbeddableDTO;
import jakarta.validation.constraints.NotNull;

public record HarvestDetailResponseDTO(@NotNull Long id,
                                       @NotNull double harvestedQuantity,
                                       @NotNull TreeEmbeddableDTO tree,
                                       @NotNull HarvestEmbeddableDTO harvest) {
}
