package com.tmgreyhat.api.User;


import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataFaker {


    @Bean
    CommandLineRunner commandLineRunnerFaker(
            UserService userService,
            EmployeeService employeeService

    ){

        User root_user = new User(
                "tapiwa",
                "tapiwa",
                "21080",
                true,
                "ROLE_ADMIN"

        );

        User hrmanager = new User(
                "hrmanager",
                "hrmanager",
                "21081",
                true,
                "ROLE_HRM"
        );
        //ROLE_HRMANAGER, ROLE_BUDGETHOLDER, ROLE_COUNTRYDIRECTOR, ROLE_ASSISTANTCOUNTRYDIRECTOR
        User budget_hold = new User(
                "budgetHolder",
                "budgetHolder",
                "21082",
                true,
                "ROLE_BH"
        );
        User country_director = new User(
                "countryDirector",
                "countryDirector",
                "21083",
                true,
                "ROLE_CD"
        );

        //ROLE_CD, ROLE_ACD, ROLE_BH, ROLE_HRM, ROLE_ADMIN, ROLE_GEN
        User assistant_country_director = new User(
                "assistantCountryDirector",
                "assistantCountryDirector",
                "21084",
                true,
                "ROLE_ACD"
        );


/**
 * String firstName,
 *             String lastName,
 *             String email,
 *             String phoneNumber,
 *             String jobGrade,
 *             String position,
 *             String systemRole,
 *             String department,
 *             boolean isSupervisor,
 *             String gender,
 *             String employeeNumber
 */



        Employee country_director_emp = new Employee(

                "Charles", "Manning",
                "cmanning@care.co.zw",
                "0774714774", "A1",
                "Country Director", "ROLE_CD",
                "Program Support",
                true, "MALE",
                "2108121"


        );
        //ROLE_CD, ROLE_ACD, ROLE_BH, ROLE_HRM, ROLE_ADMIN, ROLE_GEN
        Employee hr_manager = new Employee(

                "Patricia", "Sadomba",
                "psadomba@care.co.zw",
                "0774124112", "A1",
                "HR Manager", "ROLE_HRM",
                "Program Support",
                true, "FEMALE",
                "210841"


        );


        return args -> {

           // employeeService.addOneEmployee(country_director_emp);
            //employeeService.addOneEmployee(hr_manager);
           // userService.registerNewUser(root_user);
            //userService.registerNewUser(hrmanager);
            //userService.registerNewUser(budget_hold);
            //userService.registerNewUser(country_director);
            //userService.registerNewUser(assistant_country_director);

        };
    }

}
