package com.tmgreyhat.api.probation;

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
@Table(name = "probation_periods")
public class ProbationPeriod {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;
    private String employeeNumber;
    private boolean isComplete;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    public  ProbationPeriod(String employeeNumber){
        this.employeeNumber = employeeNumber;

    }
    public  ProbationPeriod (String employeeNumber,
                             LocalDate startDate,
                             LocalDate endDate){
        this.employeeNumber = employeeNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
