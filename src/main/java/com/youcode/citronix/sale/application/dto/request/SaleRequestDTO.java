package com.youcode.citronix.sale.application.dto.request;

public record SaleRequestDTO(  Long clientId,
         Long harvestId,
         double unitPrice,
         double quantitySold) {
}
