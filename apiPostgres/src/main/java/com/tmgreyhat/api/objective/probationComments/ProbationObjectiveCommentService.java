package com.tmgreyhat.api.objective.probationComments;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProbationObjectiveCommentService {

    private  final ProbationObjectiveCommentRepository probationObjectiveCommentRepository;

    public ProbationObjectiveCommentService(ProbationObjectiveCommentRepository
                                             probationObjectiveCommentRepository) {
        this.probationObjectiveCommentRepository = probationObjectiveCommentRepository;
    }

    public ProbationObjectiveComment addProbationObjectiveComment(ProbationObjectiveComment probationObjectiveComment){



        return this.probationObjectiveCommentRepository.save(probationObjectiveComment);
    }

    public Optional<ProbationObjectiveComment> getComment(Long id) {

        return Optional.of(probationObjectiveCommentRepository.getById(id));
    }

    public ProbationObjectiveComment updateComment(ProbationObjectiveComment p1) {


        ProbationObjectiveComment probationObjectiveComment =
                probationObjectiveCommentRepository.getById(p1.getObjectiveId());
                probationObjectiveComment.setSupervisorComment(p1.getSupervisorComment());
                probationObjectiveComment.setSupervisorRating(p1.getSupervisorRating());
      return   probationObjectiveCommentRepository.save(probationObjectiveComment);
    }
}
