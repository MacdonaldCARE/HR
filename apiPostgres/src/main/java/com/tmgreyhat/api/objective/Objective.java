package com.tmgreyhat.api.objective;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_objectives")
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long evaluationPeriodId;
    private String employeeNumber;
    private String objectiveTitle;
    private String indicator;

    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String setBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate setOn;

    public Objective(Long evaluationPeriodId,
                     String employeeNumber,
                     String objectiveTitle,
                     String indicator,
                     LocalDate startDate,
                     LocalDate endDate) {
        this.evaluationPeriodId = evaluationPeriodId;
        this.employeeNumber = employeeNumber;
        this.objectiveTitle = objectiveTitle;
        this.indicator = indicator;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
