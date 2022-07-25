package com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PIPObjectiveRepository extends JpaRepository<PIPObjective, Long> {
    @Query("select p from PIPObjective p where p.pipId = ?1")
    List<PIPObjective> findByPipId(Long pipId);
}
