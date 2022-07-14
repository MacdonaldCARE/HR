package com.tmgreyhat.api.faker;

import com.tmgreyhat.api.TemplateControllers.objectiveController.EmployeeObjectiveController;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Configuration
public class ImportFakwe {

    Logger logger = Logger.getLogger(ImportFakwe.class.getName());
    @Bean
    CommandLineRunner commandLineRunnerFaker(
            UserService userService,
            EmployeeService employeeService

    ) {
        return args -> {




    /*        List<List<String>> contactRecords = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tmbizvo\\Downloads\\Documents\\care_job_grades.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    contactRecords.add(Arrays.asList(values));

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/


/*            for(List<String> employeeRecord: contactRecords){
                String employeeNumber = employeeRecord.get(0);
                Optional<Employee> employeeOptional = employeeService.getOneOptionalEmployee(employeeNumber);

                if(employeeOptional.isPresent()){

                Employee updatedEmployee = employeeOptional.get();

                updatedEmployee.setJobGradeStep(Integer.parseInt(employeeRecord.get(1)));
                updatedEmployee.setJobGrade(employeeRecord.get(2));

                logger.info(" EMPLOYEE :: "+ updatedEmployee.getFullName()+ "  GRADE:: "+ updatedEmployee.getJobGrade()+" STEP :: "+ updatedEmployee.getJobGradeStep());

              //  employeeService.updateEmployee(updatedEmployee);

                }
            }*/



        };
    }
}
