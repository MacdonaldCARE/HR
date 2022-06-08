package com.tmgreyhat.api.evaluation;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluation_periods")
public class EvaluationPeriod {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String employeeNumber;
    private int year;
    private String quarter;

    public  EvaluationPeriod(Long id){

        this.id = id;
    }


}
