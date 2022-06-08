package com.tmgreyhat.api.objective;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveService {

    private final  ObjectiveRepository objectiveRepository;

    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }


    public Objective createObjective (Objective objective){

        return objectiveRepository.save(objective);

    }


    public List<Objective> getObjectivesByEvaluationId(Long id) {

        return objectiveRepository.findByEvaluationPeriodId(id);
    }

    public List<Objective> getObjectivesByEvaluationId(Long id, String employeeNumber) {

        return objectiveRepository.findByEvaluationPeriodIdAndEmployeeNumber(id, employeeNumber);
    }

    public Objective geObjectiveById(Long id) {

        return  objectiveRepository.findById(id).get();
    }

    public Long getEvaluationId(Long objectiveId) {

          Objective objective = objectiveRepository.getById(objectiveId);

          return  objective.getEvaluationPeriodId();

    }
}
