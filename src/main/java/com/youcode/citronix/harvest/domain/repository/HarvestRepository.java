package com.youcode.citronix.harvest.domain.repository;

import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.valueobject.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {


}