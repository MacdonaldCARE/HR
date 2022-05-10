package com.tmgreyhat.api.controller;


import com.tmgreyhat.api.departments.DepartmentService;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.jobTitles.JobTitleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final  EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobTitleService jobTitleService;

    public TemplateController(EmployeeService employeeService, DepartmentService departmentService, JobTitleService jobTitleService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobTitleService = jobTitleService;
    }

    @GetMapping("login")
    public String getLoginPage(){

        return "login";
    }

    @GetMapping("/")
    public String getMainPage(){
        return  "index";
    }

    @GetMapping("/employees")
    public String   getEmployeesView(Model model){

        model.addAttribute("employeeList", employeeService.getAllEmployees() );
        return  "employees";
    }

    @GetMapping("/employee-add")
    public String getEmployeeAddView(Model model){

        model.addAttribute("employee", new com.tmgreyhat.api.employees.Employee());
        model.addAttribute("departmentList",departmentService.getAllDepartments());
        model.addAttribute("jobTitles",jobTitleService.getAllJobTitles());
        return  "employee-add";
    }

    @PostMapping("/employee-add")
    public String addEmployee(com.tmgreyhat.api.employees.Employee employee){

        employeeService.addOneEmployee(employee);
        return  "redirect:/employees";
    }

}
