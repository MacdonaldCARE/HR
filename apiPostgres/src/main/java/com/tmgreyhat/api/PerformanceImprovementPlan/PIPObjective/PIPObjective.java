package com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "performance_improvement_objectives")
public class PIPObjective {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pipId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;
    private String targetArea;
    private  String concern;
    private String expectedOutcome;
    private String agreedActions;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate achievementDate;


}
