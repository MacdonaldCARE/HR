package com.tmgreyhat.api.objective.probationComments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProbationObjectiveCommentRepository extends JpaRepository<ProbationObjectiveComment, Long> {
    @Query("select p from ProbationObjectiveComment p where p.objectiveId = ?1")
    Optional<ProbationObjectiveComment> getByBbjectiveId(Long objectiveId);
}
