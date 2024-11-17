package com.youcode.citronix.farm.domain.entity;

import com.youcode.citronix.common.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "trees")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tree extends AbstractEntity {

    @Column(nullable = false)
    @NotNull
    private LocalDateTime plantingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @NotNull
    private Field field;

    @OneToMany(mappedBy = "tree")
    private Set<HarvestDetail> harvestDetails = new HashSet<>();
}
