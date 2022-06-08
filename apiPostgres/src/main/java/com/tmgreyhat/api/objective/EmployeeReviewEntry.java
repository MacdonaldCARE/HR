package com.tmgreyhat.api.objective;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReviewEntry {


    private String objective;
    private long objectiveId;
    private String indicator;
    private String evaluationPeriod;
    private double employeeRating;
    private String employeeComment;
    private double supervisorRating;
    private String supervisorComment;


}
