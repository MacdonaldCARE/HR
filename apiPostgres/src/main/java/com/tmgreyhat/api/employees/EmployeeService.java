package com.tmgreyhat.api.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final  EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployees() {

        return  employeeRepository.findAll();
    }

    public Employee getOneEmployee(Long id) {

        boolean departmentExists = employeeRepository.findById(id).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Employee with ID "+id+" does not exist");
        }

        return  employeeRepository.getById(id);

    }

    public Employee updateEmployee(Employee employee) {

        boolean departmentExists = employeeRepository.findById(employee.getId()).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Employee with ID "+employee.getId()+" does not exist");
        }

        return  employeeRepository.save(employee);
    }

    public Employee addOneEmployee(Employee employee) {

        boolean employeeEmailTaken = employeeRepository.findByEmail(employee.getEmail()).isPresent();

        if(employeeEmailTaken){

            throw  new IllegalStateException("Employee with email "+employee.getEmail()+" Already exist");
        }

        return  employeeRepository.save(employee);

    }

    public void deleteOneEmployee(Long id) {

        boolean departmentExists = employeeRepository.findById(id).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Employee with ID "+id+" does not exist");
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesFromDepartment(Long id) {

       return employeeRepository.findByDepartmentId(id);
    }
}
