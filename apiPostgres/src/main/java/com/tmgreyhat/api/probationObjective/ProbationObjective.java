package com.tmgreyhat.api.probationObjective;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "probation_objectives")
public class ProbationObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long probationPeriodId;
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
}
