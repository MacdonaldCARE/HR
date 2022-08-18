package com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjectiveComment;

import org.springframework.stereotype.Service;

@Service
public class PIPObjectiveCommentService {


    private  final PIPObjectiveCommentRepository pipObjectiveCommentRepository;

    public PIPObjectiveCommentService(PIPObjectiveCommentRepository pipObjectiveCommentRepository) {
        this.pipObjectiveCommentRepository = pipObjectiveCommentRepository;
    }

    public PIPObjectiveComment save(PIPObjectiveComment pipObjectiveComment){
        return pipObjectiveCommentRepository.save(pipObjectiveComment);
    }
}
