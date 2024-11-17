package com.youcode.citronix.harvest.domain.entity;

import com.youcode.citronix.common.AbstractEntity;
import com.youcode.citronix.farm.domain.valueobject.Season;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "harvests")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Harvest extends AbstractEntity {

    @Column(nullable = false)
    private LocalDateTime harvestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;
@NotNull
    private double totalQuantity;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HarvestDetail> harvestDetails = new HashSet<>();

    @OneToMany(mappedBy = "harvest")
    private Set<Sale> sales = new HashSet<>();

}