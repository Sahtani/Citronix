package com.youcode.citronix.sale.application.dto.response;

import jakarta.validation.constraints.NotNull;

public record SaleResponseDTO(@NotNull Long id,
        Long clientId,
                              Long harvestId,
                              double unitPrice,
                              double quantitySold) {
}
