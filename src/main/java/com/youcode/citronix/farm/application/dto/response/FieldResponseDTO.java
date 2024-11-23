package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.embeddable.FarmEmbeddableDTO;
import com.youcode.citronix.farm.application.dto.embeddable.TreeEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record FieldResponseDTO(@NotNull Long id,
                               @NotBlank String name,
                               @NotNull double area,
                               FarmEmbeddableDTO farm,
                               Set<TreeEmbeddableDTO> trees) {
}

