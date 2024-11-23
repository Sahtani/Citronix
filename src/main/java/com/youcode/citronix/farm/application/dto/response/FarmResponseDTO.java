package com.youcode.citronix.farm.application.dto.response;

import com.youcode.citronix.farm.application.dto.embeddable.FieldEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record FarmResponseDTO(@NotNull Long id ,
                              @NotBlank String name,
                              @NotBlank String location,
                              @NotNull
                              double totalArea,
                              @NotNull
                              LocalDateTime creationDate,
                              Set<FieldEmbeddableDTO> fields) {
}
