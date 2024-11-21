package com.youcode.citronix.harvest.application.dto.response;

public record HarvestDetailRsponseDTO(  Long id ,
         Long harvestId,
         Long treeId,
         double harvestedQuantity) {
}
