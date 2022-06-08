package com.tmgreyhat.api.position;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class PostionFaker {

    @Bean
    CommandLineRunner commandLineRunnerPosition(PositionRepository positionRepository) {


        return  args -> {

            List<Position> positions = Arrays.asList(
                    new Position("IT Manager"),
                    new Position("AP Analyst"),
                    new Position("Awards and Sub Awards Accountant"),
                    new Position("Finance and Administration Assistant"),
                    new Position("Program Support Assistant"),
                    new Position("Risk and Compliance Intern"),
                    new Position("Assistant Country  Director - Operations"),
                    new Position("Fleet Coordinator"),
                    new Position("ICT Assistant"),
                    new Position("Enumerator"),
                    new Position("Procurement Assistant"),
                    new Position("Front OfficeLocation Intern"),
                    new Position("Administration Intern"),
                    new Position("Human Resources Manager"),
                    new Position("Driver"),
                    new Position("IT/Administration Officer"),
                    new Position("Risk and Compliance Manager"),
                    new Position("Procurement Manager"),
                    new Position("Human Resources Intern"),
                    new Position("Procurement Intern"),
                    new Position("Finance Intern"),
                    new Position("ICT Manager"),
                    new Position("Awards and Sub Awards Manager"),
                    new Position("Senior Finance Manager"),
                    new Position("Accounting Manager"),
                    new Position("Motorbike Mechanic"),
                    new Position("Motor  Mechanic"),
                    new Position("Human Resources Officer"),
                    new Position("Procurement Officer")
            );

            //positionRepository.saveAll(positions);

        };
    }
}
