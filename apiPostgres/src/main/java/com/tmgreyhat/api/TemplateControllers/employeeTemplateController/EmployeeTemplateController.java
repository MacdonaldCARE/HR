package com.tmgreyhat.api.TemplateControllers.employeeTemplateController;

import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.departments.DepartmentService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.jobGrade.JobGradeService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class EmployeeTemplateController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final UserService userService;

    private final JobGradeService jobGradeService;
    private final ProjectService projectService;

    public EmployeeTemplateController(EmployeeService employeeService,
                                      DepartmentService departmentService,
                                      PositionService positionService, UserService userService, JobGradeService jobGradeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.positionService = positionService;

        this.userService = userService;
        this.jobGradeService = jobGradeService;
        this.projectService = projectService;
    }


    @GetMapping("/employees")

    public String   getEmployeesView(Model model) {

        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employeeList", employeeService.getAllEmployees() );
        return  "employees";
    }



    @PostMapping("/employees")
    public String addEmployee(Employee employee, Model model){

        model.addAttribute("loggedInUser",getLoggedInUser());
        System.out.println("##############################################################");
        System.out.println("SATRTING THE SAVE");
        System.out.println(employee);
        System.out.println("##############################################################");
        employeeService.addOneEmployee(employee);
        return  "redirect:/employees";
    }

    @PutMapping("/employees")
    public String updateEmployee(Employee employee, Model model){

        model.addAttribute("loggedInUser",getLoggedInUser());
        System.out.println("updating employee"+ employee.getEmployeeNumber());
        employeeService.updateEmployee(employee);

        return  "redirect:/employees";

    }

    @GetMapping("/employees-add")
    public String getEmployeeAddView(Model model){

        model.addAttribute("employee", new com.tmgreyhat.api.employees.Employee());
        model.addAttribute("projects",projectService.getAllProjects());
        model.addAttribute("supervisors",employeeService.getSupervisors());
       // model.addAttribute("jobTitles",jobTitleService.getAllJobTitles());
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("positions",positionService.getAllPositions());
        model.addAttribute("jobGrades",jobGradeService.getAllJobGrades());
        return  "employee-add";
    }

    @GetMapping("/employees-view/{id}")
    public  String getEmployeeViewPage(Model model, @PathVariable(name = "id") String id){

        Employee employee = employeeService.getOneEmployee(id);
        //System.out.println("Supervisor "+ employee.getSupervisor());
        //System.out.println("GOT EMPLOYEE "+ employee);
       // model.addAttribute("employeeObject", new Employee());
        model.addAttribute("employee", employee);
        model.addAttribute("loggedInUser",getLoggedInUser());
        return  "employee-view";
    }
    @GetMapping("/employees-edit/{id}")
    public  String getEmployeeEditPage(Model model, @PathVariable(name = "id") String id){

        System.out.println(" Getting for edit");
        Employee employee = employeeService.getOneEmployee(id);
        System.out.println(employee.getDepartment());
        //System.out.println("Supervisor "+ employee.getSupervisor());
        //System.out.println("GOT EMPLOYEE "+ employee);
        model.addAttribute("departmentList",departmentService.getAllDepartments());
        model.addAttribute("positions",positionService.getClass());
        model.addAttribute("supervisors",employeeService.getSupervisors());
        System.out.println(" Getting for edit");
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employeeObject", new Employee());
        model.addAttribute("employee", employee);
        model.addAttribute("loggedInUser",getLoggedInUser());
        return  "employee-edit";
    }


    private LoggedInUser getLoggedInUser(){
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
       // logger.info("User  "+ userDetails.getAuthorities() + " getting ApplicationToRecruit page");

        String userRole  = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        return new LoggedInUser(userDetails.getUsername(),userRole);

    }
}
