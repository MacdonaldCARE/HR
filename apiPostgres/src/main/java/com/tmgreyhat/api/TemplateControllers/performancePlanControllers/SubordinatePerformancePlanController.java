package com.tmgreyhat.api.TemplateControllers.performancePlanControllers;

import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective.PIPObjective;
import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjective.PIPObjectiveService;
import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjectiveComment.PIPObjectiveComment;
import com.tmgreyhat.api.PerformanceImprovementPlan.PIPObjectiveComment.PIPObjectiveCommentService;
import com.tmgreyhat.api.PerformanceImprovementPlan.PerformanceImprovementPlan;
import com.tmgreyhat.api.PerformanceImprovementPlan.PerformanceImprovementPlanService;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class SubordinatePerformancePlanController {
    Logger logger = Logger.getLogger(SubordinatePerformancePlanController.class.getName());
    private final PIPObjectiveService pipObjectiveService;
    private final UserService  userService;
    private  final EmployeeService employeeService;

    private final PIPObjectiveCommentService pipObjectiveCommentService;
    private  final PerformanceImprovementPlanService performanceImprovementPlanService;

    public SubordinatePerformancePlanController(PIPObjectiveService pipObjectiveService,
                                                UserService userService, EmployeeService employeeService,
                                                PIPObjectiveCommentService pipObjectiveCommentService,
                                                PerformanceImprovementPlanService performanceImprovementPlanService) {
        this.pipObjectiveService = pipObjectiveService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.pipObjectiveCommentService = pipObjectiveCommentService;
        this.performanceImprovementPlanService = performanceImprovementPlanService;
    }

    @GetMapping("/init-pip-update")
    public String subordinatePerformancePlan(Model model){
        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();
        Employee employee = employeeService.getOneEmployee(employeeNumber);


        List<PerformanceImprovementPlan> plans =
                performanceImprovementPlanService
                        .getAllPerformanceImprovementPlansForEmployee(employee.getEmployeeNumber());
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("plans",plans);

        model.addAttribute("PerformanceImprovementPlan",new PerformanceImprovementPlan());
        return "pip-self-review-init";
    }

    @GetMapping("/employee-view-pip-tasks")

    public String employeeViewPipTasks(Model model,
                                       PerformanceImprovementPlan performanceImprovementPlan){

        logger.info("performanceImprovementPlan "+performanceImprovementPlan);
        List<PIPObjective> objectives = pipObjectiveService.getAllPIPObjectivesForPlan(performanceImprovementPlan.getId());
        String username = getLoggedInUser().getUsername();
        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();
        Employee employee = employeeService.getOneEmployee(employeeNumber);

        logger.info("objectives for "+ performanceImprovementPlan.getId() +" Are "+objectives);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",objectives);
        model.addAttribute("employee",employee);

        return "emp-pip-objectives-list";
    }

    @GetMapping("/employee-pip-objective-update/{id}")

    public String employeePipObjectiveUpdate(Model model,
                                             @PathVariable Long id){
        logger.info("id is "+id);


        PIPObjective objective = pipObjectiveService.getObjectiveById(id);
        logger.info("objective is "+objective);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objective",objective);
        model.addAttribute("pipObjectiveComment", new PIPObjectiveComment());
        return "pip-objective-emp-update";
    }

    @PostMapping("/pip-emp-add-comment")
    public String addComment(Model model, PIPObjectiveComment pipObjectiveComment){
        logger.info("SAVING pipObjectiveComment "+pipObjectiveComment);

        pipObjectiveCommentService.save(pipObjectiveComment);
        return "redirect:/employee-view-pip-tasks?id="+pipObjectiveComment.getPipObjectiveId();
    }
}
