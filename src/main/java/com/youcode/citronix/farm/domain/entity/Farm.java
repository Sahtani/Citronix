package com.youcode.citronix.farm.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "farms")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Farm  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @NotBlank
    private String location;
    @NotNull
    private double totalArea;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private Set<Field> fields = new HashSet<>();
    public Farm(Long id, String name, String location, double totalArea) {
        this.id =id;
        this.name = name;
        this.location = location;
        this.totalArea = totalArea;
    }

}
