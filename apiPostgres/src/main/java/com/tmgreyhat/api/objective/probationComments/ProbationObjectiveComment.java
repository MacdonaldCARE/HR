package com.tmgreyhat.api.objective.probationComments;


import com.tmgreyhat.api.probationObjective.ProbationObjective;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "probation_objective_comments")
public class ProbationObjectiveComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long objectiveId;
    private double employeeRating;
    private double supervisorRating;
    private String employeeComment;

    private String supervisorComment;
    private LocalDate submittedOn;
    private LocalDate reviewedOn;

    public ProbationObjectiveComment(Long id,
                            double employeeRating,
                            String employeeComment) {
        this.id = id;
        this.employeeRating = employeeRating;
        this.employeeComment = employeeComment;
    }

    public  ProbationObjectiveComment (double supervisorRating,
                                       String supervisorComment){
        this.supervisorRating =supervisorRating;
        this.supervisorComment = supervisorComment;
    }


}
