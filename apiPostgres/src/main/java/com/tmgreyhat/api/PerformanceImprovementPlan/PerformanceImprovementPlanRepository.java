package com.tmgreyhat.api.PerformanceImprovementPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PerformanceImprovementPlanRepository extends JpaRepository<PerformanceImprovementPlan, Long> {
    @Query("select p from PerformanceImprovementPlan p where p.employeeNumber = ?1")
    List<PerformanceImprovementPlan> findAllByEmployeeNumber(String employeeNumber);
    @Query("select p from PerformanceImprovementPlan p where p.employeeNumber = ?1 and p.isComplete = false")
    Optional<PerformanceImprovementPlan> findByEmployeeNumberAndStatusIsOpen(String employeeNumber);
}
