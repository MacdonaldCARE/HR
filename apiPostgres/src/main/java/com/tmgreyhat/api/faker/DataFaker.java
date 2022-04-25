package com.tmgreyhat.api.faker;

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
public class DataFaker {

    @Bean
    CommandLineRunner commandLineRunnerDataFaker(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository,
            JobTitleRepository jobTitleRepository
    ){

        List<Department> departmentList = Arrays.asList(
                new Department("IT", "Information Technology"),
                new Department("HR", "Human Resource"),
                new Department("Finance", "Finance")

        );


        Department itDepartment = departmentRepository.getById(1L);
        Department hrDepartment = departmentRepository.getById(2L);
        Department financeDepartment = departmentRepository.getById(3L);


            JobTitle javadeve = new JobTitle("Java Developer", "A1");
            JobTitle hrmanager = new JobTitle("HR Manager", "A2");
            JobTitle financeManager = new JobTitle("Finance Manager", "A3");
            JobTitle itManager = new JobTitle("IT Manager", "A4");

         jobTitleRepository.save(javadeve);
         jobTitleRepository.save(hrmanager);
         jobTitleRepository.save(financeManager);
         jobTitleRepository.save(itManager);
        Employee supervisor = new Employee(

                "King",
                "Muswati",
                "king@care.co.zw",
                hrmanager,
                hrDepartment,
                "MALE"
        );


        departmentRepository.saveAll(departmentList);
        employeeRepository.save(supervisor);
        Employee employee = new Employee(
                "Sammy",
                "Queen",
                "squen@care.co.zw",
                javadeve,
                itDepartment,
                supervisor,
                "MALE"
        );
        Employee employee1 = new Employee(
                "Trish",
                "king",
                "trish@care.co.zw",
                financeManager,
                hrDepartment,
                supervisor,
                "FEMALE"
        );

        List<Employee> subordinates = Arrays.asList(employee, employee1);
        Employee employee2 = new Employee(
                "Dave",
                "Sam",
                "dave@care.co.zw",
                itManager,
                financeDepartment,
                supervisor,
                "MALE"
        );
        employeeRepository.saveAll(
                List.of(
                        employee,
                        employee1,
                        employee2
                )
        );

        return  args -> {

          //  departmentRepository.saveAll(departmentList);
        };


    }



}

