package com.tmgreyhat.api.probationObjective;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbationObjectiveService {
    private  final ProbationObjectiveRepository probationObjectiveRepository;

    public ProbationObjectiveService(ProbationObjectiveRepository probationObjectiveRepository) {
        this.probationObjectiveRepository = probationObjectiveRepository;
    }

    public ProbationObjective addProbationObjective(ProbationObjective probationObjective) {
        return probationObjectiveRepository.save(probationObjective);
    }

    public List<ProbationObjective> getProbationObjectivesForEmployee(String employeeNumber) {
        return probationObjectiveRepository.findByEmployeeNumber(employeeNumber);
    }


    public ProbationObjective getOneObjective(Long id) {

        return  probationObjectiveRepository.getById(id);
    }


    public ProbationObjective  updateObjective(ProbationObjective probationObjective) {

        return  probationObjectiveRepository.save(probationObjective);
    }
}
