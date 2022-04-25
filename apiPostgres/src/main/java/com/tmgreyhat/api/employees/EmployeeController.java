package com.tmgreyhat.api.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){

        return  employeeService.getAllEmployees();
    }
    @GetMapping(path = "{id}")
    public  Employee getOneEmployee(@PathVariable(name = "id") Long id){

        return  employeeService.getOneEmployee(id);

    }
    @GetMapping(path = "/departments/{id}")
    public  List<Employee> getEmployeesFromDepartment(@PathVariable(name = "id") Long id){


        return  employeeService.getEmployeesFromDepartment(id);
    }

    @PostMapping
    public  Employee createOneEmployee(@RequestBody Employee employee){

        return  employeeService.addOneEmployee(employee);
    }

    @PutMapping
    public  Employee updateEmployee(@RequestBody Employee employee){

        return  employeeService.updateEmployee(employee);
    }

    @DeleteMapping(path = "{id}")
    public  void deleteEmployee(@PathVariable (name = "id") Long id){

        employeeService.deleteOneEmployee(id);
    }

}
