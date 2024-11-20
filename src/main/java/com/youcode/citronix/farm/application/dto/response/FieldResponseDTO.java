package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.Embeddable.EmbeddableFarmDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record FieldResponseDTO(@NotNull Long id,
                               @NotBlank String name,
                               @NotNull double area, EmbeddableFarmDTO farm,
                               Set<EmbeddableFarmDTO> trees) {
}

