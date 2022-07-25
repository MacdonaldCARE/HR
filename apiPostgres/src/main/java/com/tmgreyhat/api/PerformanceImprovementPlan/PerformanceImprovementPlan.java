package com.tmgreyhat.api.PerformanceImprovementPlan;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_improvement_plans")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceImprovementPlan {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private String employeeNumber;
    private boolean isComplete;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

}
