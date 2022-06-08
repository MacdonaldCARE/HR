package com.tmgreyhat.api.jobGrade;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JobGradeFaker {
    @Bean
    CommandLineRunner commandLineRunnerJobGradeFaker(
            JobGradeRepository jobGradeRepository) {
        return args->{

            /**
             * A1
             * A2
             * A3
             * B1
             * B2
             * B3
             * B4
             * B5
             * C1
             * C2
             * C3
             * C4
             * C5
             * D1
             * D2
             * D3
             * D4
             * E1
             * E2
             * E3
             * E4
             */
            List<JobGrade> jobGrades = Arrays.asList(
                   new JobGrade( "A1"),
                   new JobGrade( "A2"),
                   new JobGrade( "A3"),
                   new JobGrade( "B1"),
                   new JobGrade( "B2"),
                   new JobGrade( "B3"),
                   new JobGrade( "B4"),
                   new JobGrade( "B5"),
                   new JobGrade( "C1"),
                   new JobGrade( "C2"),
                   new JobGrade( "C3"),
                   new JobGrade( "C4"),
                   new JobGrade( "C5"),
                   new JobGrade( "D1"),
                   new JobGrade( "D2"),
                   new JobGrade( "D3"),
                   new JobGrade( "D4"),
                   new JobGrade( "E1"),
                   new JobGrade( "E2"),
                   new JobGrade( "E3"),
                   new JobGrade( "34")

            );

            //jobGradeRepository.saveAll(jobGrades);

        };


    }
}
