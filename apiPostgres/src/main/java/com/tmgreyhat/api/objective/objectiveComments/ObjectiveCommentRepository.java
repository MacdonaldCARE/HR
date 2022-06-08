package com.tmgreyhat.api.objective.objectiveComments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectiveCommentRepository extends JpaRepository<ObjectiveComment, Long> {
    @Query("select o from ObjectiveComment o where o.objectiveId = ?1")
    List<ObjectiveComment> getByObjectiveId(Long objectiveId);

}
