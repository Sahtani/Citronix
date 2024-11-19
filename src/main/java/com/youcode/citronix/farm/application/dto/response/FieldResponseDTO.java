package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.Embedded.EmbeddedFarmDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FieldResponseDTO(@NotNull Long id,
                               @NotBlank String name,
                               @NotNull double area,
                               @NotNull EmbeddedFarmDTO embeddedFarmDTO) {
}

