package com.tmgreyhat.api.probation;

import org.springframework.stereotype.Service;

@Service
public class ProbationPeriodService {

    private final  ProbationRepository probationRepository;

    public ProbationPeriodService(ProbationRepository probationRepository) {
        this.probationRepository = probationRepository;
    }

    public ProbationPeriod addProbationPeriod(ProbationPeriod probationPeriod) {

        if(probationRepository.existsByEmployeeNumber(probationPeriod.getEmployeeNumber())){
            return probationRepository.findByEmployeeNumber(probationPeriod.getEmployeeNumber()).get();

        }
        return  probationRepository.save(probationPeriod);
    }

    public ProbationPeriod getProbationPeriod(String employeeNumber) {

        return  probationRepository.findByEmployeeNumber(employeeNumber).get();
    }
}
