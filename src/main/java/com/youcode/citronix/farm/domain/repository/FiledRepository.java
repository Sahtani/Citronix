package com.youcode.citronix.farm.domain.repository;

import com.youcode.citronix.farm.domain.entity.Field;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiledRepository extends JpaRepository<Field, Long> {
    boolean existsByName(@NotBlank String name);
}
