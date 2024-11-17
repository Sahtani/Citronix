package com.youcode.citronix.harvest.domain.entity;

import com.youcode.citronix.common.AbstractEntity;
import com.youcode.citronix.farm.domain.entity.Tree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "harvest_details")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class HarvestDetail extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tree tree;

    @Column(name = "harvested_quantity")
    private double harvestedQuantity;


}
