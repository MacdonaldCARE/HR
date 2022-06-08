package com.tmgreyhat.api.project;

import com.tmgreyhat.api.departments.Department;
import com.tmgreyhat.api.departments.DepartmentRepository;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeRepository;
import com.tmgreyhat.api.jobTitles.JobTitle;
import com.tmgreyhat.api.jobTitles.JobTitleRepository;
import org.checkerframework.checker.units.qual.K;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProjectFaker {

    @Bean
    CommandLineRunner commandLineRunnerDataFaker(
            ProjectRepository projectRepository

    ){
        return  args -> {


               /* List<Project> projectList = Arrays
                        .asList(
                                new Project("ECRIMS1", "ECRIMS", "ECRIMS"),
                                new Project("PS1", "Program Support", "Program Support"),
                                new Project("ERP", "ERP Cyclone Idai", "ERP Cyclone Idai"),
                                new Project("ECRAS", "ECRAS", "ECRAS"),
                                new Project("OFDA COVID", "OFDA COVID", "OFDA COVID"),
                                new Project("WFP URBAN", "WFP URBAN", "WFP URBAN"),
                                new Project("ECHO HIP", "ECHO HIP", "ECHO HIP"),
                                new Project("Start4Girls", "Start4Girls", "Start4Girls")
                        );*/


               // projectRepository.saveAll(projectList);
        };


    }



}


