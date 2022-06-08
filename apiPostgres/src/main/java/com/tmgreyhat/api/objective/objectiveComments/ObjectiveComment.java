package com.tmgreyhat.api.objective.objectiveComments;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_objective_comments")
public class ObjectiveComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long objectiveId;
    private double employeeRating;
    private double supervisorRating;
    private String employeeComment;
   // private String areasToImprove;
    private String supervisorComment;
    private LocalDate submittedOn;
    private LocalDate reviewedOn;

    public ObjectiveComment(Long id,
                            double employeeRating,
                            String employeeComment) {
        this.id = id;
        this.employeeRating = employeeRating;
        this.employeeComment = employeeComment;
    }

    public  ObjectiveComment (double supervisorRating, String supervisorComment){
        this.supervisorRating =supervisorRating;
        this.supervisorComment = supervisorComment;
    }
}
