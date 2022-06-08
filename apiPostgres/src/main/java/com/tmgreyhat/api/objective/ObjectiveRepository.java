package com.tmgreyhat.api.objective;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    @Query("select o from Objective o where o.evaluationPeriodId = ?1")
    List<Objective> findByEvaluationPeriodId(Long evaluationPeriodId);

    @Query("select o from Objective o where o.evaluationPeriodId = ?1 and o.employeeNumber = ?2")
    List<Objective> findByEvaluationPeriodIdAndEmployeeNumber(Long evaluationPeriodId, String employeeNumber);


}
