package com.tmgreyhat.api.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/care/api/casual")
public class CasualController {

    class CasualEmployee{
        String EmployeeNumber;
        String FirstName;
        String LastName;

        CasualEmployee(String EmployeeNumber,
                       String FirstName,
                       String LastName){
            this.EmployeeNumber = EmployeeNumber;
            this.FirstName = FirstName;
            this.LastName = LastName;
        }

        public String getEmployeeNumber() {
            return EmployeeNumber;
        }

        public void setEmployeeNumber(String employeeNumber) {
            EmployeeNumber = employeeNumber;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }
    }

    class Request{

        String search_term;

        public String getSearch_term() {
            return search_term;
        }

        public void setSearch_term(String search_term) {
            this.search_term = search_term;
        }

        public  Request(){}
    }

    List<CasualEmployee> casualEmployeeList = new ArrayList<>();
    @GetMapping
    public  ResponseEntity getAllCasualEmployees(){


        casualEmployeeList.add(new CasualEmployee("12345", "John", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12346", "Jane", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12347", "Jack", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12348", "Jill", "Doe"));

        return  ResponseEntity.status(HttpStatus.OK).body(casualEmployeeList);
    }

    @GetMapping("/search")
    public  ResponseEntity searchForEmployee(@RequestBody Request request){

        List<CasualEmployee> searchResult = new ArrayList<>();
        casualEmployeeList.add(new CasualEmployee("12345", "John", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12346", "Jane", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12347", "Jack", "Doe"));
        casualEmployeeList.add(new CasualEmployee("12348", "Jill", "Doe"));

        casualEmployeeList.stream().filter(casualEmployee -> casualEmployee.getFirstName().contains(request.getSearch_term())).forEach(searchResult::add);
        return  ResponseEntity.status(HttpStatus.OK).body(searchResult);

    }

}
