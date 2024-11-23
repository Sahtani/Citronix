package com.youcode.citronix.sale.application.dto.embeddable;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleEmbeddableDTO(@NotNull Long id,
                                @NotNull
                                LocalDateTime saleDate,
                                @NotNull double unitPrice
) {
}
