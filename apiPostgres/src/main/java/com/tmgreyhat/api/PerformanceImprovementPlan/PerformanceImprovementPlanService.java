package com.tmgreyhat.api.PerformanceImprovementPlan;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceImprovementPlanService {

    private final PerformanceImprovementPlanRepository repository;

    public PerformanceImprovementPlanService
            (PerformanceImprovementPlanRepository repository) {
        this.repository = repository;
    }

    public PerformanceImprovementPlan
    addPerformanceImprovementPlan(PerformanceImprovementPlan plan){

        //check if the plan already exists
       if(getEmployeeImprovementPlan(plan.getEmployeeNumber()).isPresent()){
          return  getEmployeeImprovementPlan(plan.getEmployeeNumber()).get();
       }

        return repository.save(plan);

    }




    public Optional<PerformanceImprovementPlan>
        getEmployeeImprovementPlan(String employeeNumber){
        return  repository.findByEmployeeNumberAndStatusIsOpen(employeeNumber);
    }


    public PerformanceImprovementPlan getPlanById(Long pipId) {

        return  repository.getById(pipId);
    }
}
