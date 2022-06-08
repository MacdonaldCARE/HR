package com.tmgreyhat.api.evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationPeriodRepository extends JpaRepository<EvaluationPeriod, Long> {
    @Query("select (count(e) > 0) from EvaluationPeriod e " +
            "where upper(e.employeeNumber) = upper(?1) and e.year = ?2 and upper(e.quarter) = upper(?3)")
    boolean existsByEmployeeNumberYearAndQuarter(String employeeNumber, int year, String quarter);

    @Query("select e from EvaluationPeriod e " +
            "where upper(e.employeeNumber) = upper(?1) and e.year = ?2 and upper(e.quarter) = upper(?3)")
    EvaluationPeriod findByEmployeeNumberYearAndQuarter(String employeeNumber, int year, String quarter);

    @Query("select e from EvaluationPeriod e where e.employeeNumber = ?1")
    List<EvaluationPeriod> findByEmployeeNumber(String employeeNumber);
}
