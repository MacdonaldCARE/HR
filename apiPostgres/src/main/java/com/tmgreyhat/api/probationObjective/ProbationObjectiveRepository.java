package com.tmgreyhat.api.probationObjective;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProbationObjectiveRepository extends JpaRepository<ProbationObjective, Long> {
    @Query("select p from ProbationObjective p where p.employeeNumber = ?1")
    List<ProbationObjective> findByEmployeeNumber(String employeeNumber);



}
