package com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PIPObjectiveService {

    private  final PIPObjectiveRepository repository;

    public PIPObjectiveService(PIPObjectiveRepository repository) {
        this.repository = repository;
    }


    public List<PIPObjective> getObjectivesByPIPId(Long pipId) {

        return repository.findByPipId(pipId);
    }

    public PIPObjective addPIPObjective(PIPObjective pipObjective) {

        return  repository.save(pipObjective);
    }

    public List<PIPObjective> getAllPIPObjectivesForPlan(Long id) {


        return  repository.findByPipId(id);
    }

    public PIPObjective getObjectiveById(Long id) {

        return repository.findById(id).orElse(null);
    }
}
