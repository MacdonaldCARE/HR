package com.tmgreyhat.api.jobGrade;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "job_grades")
public class JobGrade {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;
    private String jobGrade;

    public  JobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }
}
