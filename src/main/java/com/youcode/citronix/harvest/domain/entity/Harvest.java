package com.youcode.citronix.harvest.domain.entity;

import com.youcode.citronix.harvest.domain.enums.Season;
import com.youcode.citronix.sale.domain.entity.Sale;
import jakarta.persistence.*;
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
public class Harvest  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime harvestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;
    private double totalQuantity;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private Set<HarvestDetail> harvestDetails = new HashSet<>();

    @OneToMany(mappedBy = "harvest",cascade = CascadeType.ALL)
    private Set<Sale> sales = new HashSet<>();

    public Harvest(LocalDateTime harvestDate , Season season, Long id) {
        this.harvestDate = harvestDate;
        this.season = season;
        this.id = id;

    }
}
