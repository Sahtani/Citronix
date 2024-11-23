package com.youcode.citronix.harvest.application.dto.embeddable;

import jakarta.validation.constraints.NotNull;

public record HarvestDetailEmbeddableDTO(@NotNull Long id,
                                         @NotNull double harvestedQuantity) {
}
