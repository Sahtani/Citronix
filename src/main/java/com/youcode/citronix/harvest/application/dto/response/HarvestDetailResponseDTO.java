package com.youcode.citronix.harvest.application.dto.response;

import jakarta.validation.constraints.NotNull;

public record HarvestDetailResponseDTO(@NotNull Long id,
                                       @NotNull Long harvestId,
                                       @NotNull Long treeId,
                                       @NotNull double harvestedQuantity) {
}
