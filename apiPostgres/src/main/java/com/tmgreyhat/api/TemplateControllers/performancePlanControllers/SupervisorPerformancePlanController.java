package com.tmgreyhat.api.TemplateControllers.performancePlanControllers;


import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective.PIPObjective;
import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective.PIPObjectiveService;
import com.tmgreyhat.api.PerformanceImprovementPlan.PerformanceImprovementPlan;
import com.tmgreyhat.api.PerformanceImprovementPlan.PerformanceImprovementPlanService;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class SupervisorPerformancePlanController {


    Logger logger = Logger.getLogger(SupervisorPerformancePlanController.class.getName());
    private final UserService userService;
    private final EmployeeService employeeService;

    private final PIPObjectiveService pipObjectiveService;
    private  final PerformanceImprovementPlanService performanceImprovementPlanService;

    public SupervisorPerformancePlanController(UserService userService,
                                               EmployeeService employeeService,
                                               PIPObjectiveService pipObjectiveService,
                                               PerformanceImprovementPlanService performanceImprovementPlanService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.pipObjectiveService = pipObjectiveService;
        this.performanceImprovementPlanService = performanceImprovementPlanService;
    }

    @GetMapping("/initialise-pip")
    public  String pipInitialSetUpPage(Model model){

        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();

        List<Employee> subordinates =  employeeService.getSubordinates(employeeNumber);

        logger.info("subordinates for "+ employeeNumber +" Are "+subordinates);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employees",subordinates);
        model.addAttribute("PerformanceImprovementPlan", new PerformanceImprovementPlan());

        return  "pip-setting-init";

    }

    @GetMapping("/set-pip-tasks")
    public  String setPipTasks(Model model,
                               PerformanceImprovementPlan performanceImprovementPlan){
        String username = getLoggedInUser().getUsername();


        PerformanceImprovementPlan savedPip =
                performanceImprovementPlanService.addPerformanceImprovementPlan(performanceImprovementPlan);
        List<PIPObjective> pipObjectiveList =
                pipObjectiveService.getObjectivesByPIPId(savedPip.getId());

        Employee employee = employeeService.getOneEmployee(performanceImprovementPlan.getEmployeeNumber());
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",pipObjectiveList);
        model.addAttribute("employee",employee);
        model.addAttribute("PIPObjective",new PIPObjective());
        model.addAttribute("savedPip", savedPip);

        return  "pip-setting-page";

    }

    @PostMapping("/add-pip-objective")
    public String addPIPObjective(Model model, PIPObjective pipObjective){


        PerformanceImprovementPlan plan =
                performanceImprovementPlanService.getPlanById(pipObjective.getPipId());

        Employee employee =
                employeeService.getOneEmployee(plan.getEmployeeNumber());
        pipObjective.setCreatedOn(LocalDate.now());
        pipObjectiveService.addPIPObjective(pipObjective);
        List<PIPObjective> pipObjectiveList =
                pipObjectiveService.getObjectivesByPIPId(pipObjective.getPipId());
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",pipObjectiveList);
        model.addAttribute("employee",employee);
        model.addAttribute("savedPip",plan);

        model.addAttribute("PIPObjective",new PIPObjective());
        return "pip-setting-page";
    }
}
