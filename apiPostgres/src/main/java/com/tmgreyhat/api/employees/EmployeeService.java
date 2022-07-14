package com.tmgreyhat.api.employees;

import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final  EmployeeRepository employeeRepository;
    private final UserService userService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }


    public List<Employee> getAllEmployees() {

        return  employeeRepository.findAll();
    }
    public  List<Employee> getSupervisors(){

        return  employeeRepository.getAllSupervisors();
    }

    public Employee getOneEmployee(String id) {

        boolean employeeExists = employeeRepository
                .findById(id).isPresent();

        if(!employeeExists){

            throw  new IllegalStateException("Employee with ID "+id+" does not exist");
        }

        return  employeeRepository.getById(id);

    }


    public Optional<Employee> getOneOptionalEmployee(String id){

        return  employeeRepository.findById(id);
    }

    public Employee updateEmployee(Employee employee) {

        boolean departmentExists = employeeRepository
                .findById(employee.getEmployeeNumber()).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Employee with ID "+employee.getEmployeeNumber()+" does not exist");
        }

        return  employeeRepository.save(employee);
    }

    public Employee addOneEmployee(Employee employee) {

        boolean employeeEmailTaken = employeeRepository
                .findByEmail(employee.getEmail()).isPresent();

        if(employeeEmailTaken){

            throw  new IllegalStateException("Employee with email "+employee.getEmail()+" Already exist");
        }

      //  String userName = employee.getFirstName().toLowerCase()+"."+employee.getLastName().toLowerCase();

        String names[] = employee.getFullName().split(" ");
        String userName = names[0].toLowerCase()+"."+names[1].toLowerCase();
        if(userService.checkIfUserExists(userName)){

            throw  new IllegalStateException("Employee with username "+userName+" Already exist");
        }


        User new_system_user = new User(
                userName,
                userName,
                employee.getEmployeeNumber(),
                true,
                employee.getSystemRole()
        );


        userService.registerNewUser(new_system_user);
        return  employeeRepository.save(employee);

    }

    public void deleteOneEmployee(String id) {

        boolean departmentExists = employeeRepository.findById(id).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Employee with ID "+id+" does not exist");
        }
        employeeRepository.deleteById(id);
    }

/*    public List<Employee> getEmployeesFromDepartment(Long id) {

       return employeeRepository.findByDepartmentId(id);
    }*/

    public List<Employee> getSubordinates(String employeeNumber) {

        return  employeeRepository.findEmployeeSupervisedBy(employeeNumber);
    }
}
