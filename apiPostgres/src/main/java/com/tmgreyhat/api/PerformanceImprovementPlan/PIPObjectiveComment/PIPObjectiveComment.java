
package com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjectiveComment;

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
@Table(name = "pip_objective_comments")
public class PIPObjectiveComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pipObjectiveId;
    private double employeeRating;
    private double supervisorRating;
    private String employeeComment;

    private String supervisorComment;
    private LocalDate submittedOn;
    private LocalDate reviewedOn;

    public PIPObjectiveComment(Long id,
                                     double employeeRating,
                                     String employeeComment) {
        this.id = id;
        this.employeeRating = employeeRating;
        this.employeeComment = employeeComment;
    }

    public  PIPObjectiveComment (double supervisorRating,
                                       String supervisorComment){
        this.supervisorRating =supervisorRating;
        this.supervisorComment = supervisorComment;
    }


}
