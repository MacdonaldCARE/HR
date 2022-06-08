package com.tmgreyhat.api.evaluation;

import com.tmgreyhat.api.objective.Objective;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationPeriodService {

    private EvaluationPeriodRepository evaluationPeriodRepository;

    public EvaluationPeriodService(EvaluationPeriodRepository evaluationPeriodRepository) {
        this.evaluationPeriodRepository = evaluationPeriodRepository;
    }

    public EvaluationPeriod addEvaluationPeriod(EvaluationPeriod evaluationPeriod) {

        if(evaluationPeriodRepository.existsByEmployeeNumberYearAndQuarter(
                evaluationPeriod.getEmployeeNumber(),
                evaluationPeriod.getYear(),
                evaluationPeriod.getQuarter())){

            return  evaluationPeriodRepository.findByEmployeeNumberYearAndQuarter(
                    evaluationPeriod.getEmployeeNumber(),
                    evaluationPeriod.getYear(),
                    evaluationPeriod.getQuarter());


        }
        return evaluationPeriodRepository.save(evaluationPeriod);
    }


    public EvaluationPeriod getEvaluationPeriod(Long evaluationPeriodId) {

        return  evaluationPeriodRepository.getById(evaluationPeriodId);
    }

    public  EvaluationPeriod getEvaluationPeriod(String employeeNumber, int year , String quarter){


        return  evaluationPeriodRepository.findByEmployeeNumberYearAndQuarter(employeeNumber, year, quarter);
    }

    public List<EvaluationPeriod> getEvaluationPeriodsForEmployee(String employeeNumber) {

        return evaluationPeriodRepository.findByEmployeeNumber(employeeNumber);
    }
}
