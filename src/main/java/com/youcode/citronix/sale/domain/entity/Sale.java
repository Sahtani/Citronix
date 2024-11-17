package com.youcode.citronix.sale.domain.entity;

import com.youcode.citronix.common.AbstractEntity;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Sale extends AbstractEntity {

    @Column(nullable = false)
    @NotNull
    private LocalDateTime saleDate;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = false)
    private String customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @NotNull
    private double quantitySold;


}
