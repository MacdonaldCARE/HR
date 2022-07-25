package com.tmgreyhat.api.probation;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProbationRepository extends JpaRepository<ProbationPeriod, Long> {
    boolean existsByEmployeeNumber(String employeeNumber);
    Optional<ProbationPeriod> findByEmployeeNumber(String employeeNumber);

}
