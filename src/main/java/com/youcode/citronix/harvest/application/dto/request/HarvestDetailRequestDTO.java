package com.youcode.citronix.harvest.application.dto.request;

public record HarvestDetailRequestDTO(Long harvestId,
                                      Long treeId,
                                      double harvestedQuantity) {
}
