package com.youcode.citronix.farm.domain.repository;

import com.youcode.citronix.farm.domain.entity.Farm;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    boolean existsByName(@NotBlank String name);
}
