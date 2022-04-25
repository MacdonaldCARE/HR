package com.tmgreyhat.api.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments(){

        return  departmentService.getAllDepartments();
    }
    @GetMapping(path = "{id}")
    public  Department getOneDepartment(@PathVariable(name = "id") Long id){

        return  departmentService.getOneDepartment(id);

    }
    @PostMapping
    public  Department createOneDepartment(@RequestBody Department department){

        return  departmentService.createDepartment(department);
    }

    @PutMapping
    public  Department updateDepartment(@RequestBody Department department){

        return  departmentService.updateDepartment(department);
    }

    @DeleteMapping  (path = "{id}")
    public void deleteDepartment (@PathVariable(name = "id") Long id){

        departmentService.deleteDepartment(id);
    }


}
