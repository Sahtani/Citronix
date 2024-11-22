package com.youcode.citronix.harvest.application.dto.response;

import jakarta.validation.constraints.NotNull;

public record EmbeddableHarvestDetailDTO(@NotNull Long id,
                                         @NotNull Long treeId,
                                         @NotNull double harvestedQuantity) {
}
