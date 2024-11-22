package com.youcode.citronix.sale.application.dto.request;

import java.time.LocalDateTime;

public record SaleRequestDTO(
        Long harvestId,
        double unitPrice,
        double quantitySold,
        LocalDateTime saleDate,
        String customer) {
}
