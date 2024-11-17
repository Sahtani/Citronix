package com.youcode.citronix.farm.domain.repository;

import com.youcode.citronix.farm.domain.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
