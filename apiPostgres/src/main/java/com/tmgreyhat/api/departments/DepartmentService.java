package com.tmgreyhat.api.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final  DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {

        return  departmentRepository.findAll();
    }

    public Department getOneDepartment(Long id) {
        boolean departmentExists = departmentRepository.findById(id).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Department with ID "+id+" does not exist");
        }

        return  departmentRepository.getById(id);
    }

    public Department createDepartment(Department department) {

        boolean departmentExits = departmentRepository.findByName(department.getName()).isPresent();

        if(departmentExits){

            throw  new IllegalStateException(" Department with name "+department.getName()+" exists");
        }

        return  departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        boolean departmentExists = departmentRepository.findById(department.getId()).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Department with ID "+department.getId()+" does not exist");
        }

        return   departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        boolean departmentExists = departmentRepository.findById(id).isPresent();

        if(!departmentExists){

            throw  new IllegalStateException("Department with ID "+id+" does not exist");
        }

        departmentRepository.deleteById(id);
    }
}
