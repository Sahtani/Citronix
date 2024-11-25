package com.youcode.citronix.harvest.domain.repository;

import com.youcode.citronix.farm.domain.entity.Tree;
import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import com.youcode.citronix.harvest.domain.valueobject.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {

    boolean existsByTreeIdAndHarvestSeason(Long treeId, Season season);

    List<HarvestDetail> findByTreeIn(List<Tree> trees);

    boolean existsByHarvestId(Long harvestId);
}
