package com.tmgreyhat.api.objective.objectiveComments;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveCommentService {

    private final ObjectiveCommentRepository objectiveCommentRepository;

    public ObjectiveCommentService(ObjectiveCommentRepository objectiveCommentRepository) {
        this.objectiveCommentRepository = objectiveCommentRepository;
    }


    public  ObjectiveComment createObjectiveComment(ObjectiveComment objectiveComment){

        return  objectiveCommentRepository.save(objectiveComment);
    }

    public List<ObjectiveComment> getObjectiveComments(Long id) {

        return  objectiveCommentRepository.getByObjectiveId(id);
    }

    public ObjectiveComment supervisorUpdate(ObjectiveComment objectiveComment) {
        long  objectiveId = objectiveComment.getObjectiveId();

        //get comment with this objective ID

        ObjectiveComment comment =new ObjectiveComment();

        if(!objectiveCommentRepository.getByObjectiveId(objectiveId).isEmpty()){
            comment =objectiveCommentRepository.getByObjectiveId(objectiveId).get(0);
        }
        comment.setSupervisorComment(objectiveComment.getSupervisorComment());
        comment.setSupervisorRating(objectiveComment.getSupervisorRating());

        return   objectiveCommentRepository.save(comment);

    }
}
