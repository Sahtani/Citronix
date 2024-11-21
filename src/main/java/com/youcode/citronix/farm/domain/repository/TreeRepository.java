package com.youcode.citronix.farm.domain.repository;

import com.youcode.citronix.farm.domain.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    @Query("SELECT COUNT(t) FROM Tree t WHERE t.field.id = :fieldId")
    long countByFieldId(@Param("fieldId") Long fieldId);

    List<Tree> findAllByFieldId(Long fieldId);
}
