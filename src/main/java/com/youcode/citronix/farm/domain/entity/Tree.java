package com.youcode.citronix.farm.domain.entity;

import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trees")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tree  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime plantingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @NotNull
    private Field field;

    @OneToMany(mappedBy = "tree")
    private Set<HarvestDetail> harvestDetails = new HashSet<>();

    public int calculateAge() {
        return Period.between(LocalDate.from(this.plantingDate), LocalDate.now()).getYears();
    }

    public double calculateAnnualProductivity() {
        int age = calculateAge();
        if (age < 3) {
            return 2.5; // kg / season
        } else if (age <= 10) {
            return 12; // kg / season
        } else {
            return 20; // kg / season
        }
    }
}
