package com.youcode.citronix.harvest.domain.repository;

import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDetailRepositroy extends JpaRepository<HarvestDetail, Long> {
}
