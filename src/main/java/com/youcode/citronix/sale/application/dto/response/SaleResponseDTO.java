package com.youcode.citronix.sale.application.dto.response;

import com.youcode.citronix.harvest.application.dto.response.EmbeddableHarvestDTO;
import jakarta.validation.constraints.NotNull;

public record SaleResponseDTO(@NotNull Long id,
                              double unitPrice,
                              double quantitySold,
                              double revenue,
                              @NotNull EmbeddableHarvestDTO harvest) {
}
