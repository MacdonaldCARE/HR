package com.tmgreyhat.api.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {

        return args -> {
            repository.save(new Student(

                    "tapiwa",
                    "tapiwa@gmail.com",
                    LocalDate.of(1994, Month.SEPTEMBER, 27)
            ));
        };
    }
}
