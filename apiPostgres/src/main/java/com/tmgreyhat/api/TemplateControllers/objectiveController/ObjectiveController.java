package com.tmgreyhat.api.TemplateControllers.objectiveController;

import com.tmgreyhat.api.objective.EmployeeReviewEntry;
import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.evaluation.EvaluationPeriod;
import com.tmgreyhat.api.evaluation.EvaluationPeriodService;
import com.tmgreyhat.api.objective.Objective;
import com.tmgreyhat.api.objective.ObjectiveService;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveComment;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveCommentService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ObjectiveController {

    Logger logger = Logger.getLogger(ObjectiveController.class.getName());

    private final EmployeeService employeeService;
    private final  ProjectService projectService;
    private final PositionService positionService;
    private final UserService userService;
    private  final ObjectiveService objectiveService;
    private final EvaluationPeriodService evaluationPeriodService;
    private final ObjectiveCommentService objectiveCommentService;
    public ObjectiveController(EmployeeService employeeService,
                               ProjectService projectService,
                               PositionService positionService, UserService userService,
                               ObjectiveService objectiveService,
                               EvaluationPeriodService evaluationPeriodService,
                               ObjectiveCommentService objectiveCommentService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.positionService = positionService;
        this.userService = userService;
        this.objectiveService = objectiveService;
        this.evaluationPeriodService = evaluationPeriodService;
        this.objectiveCommentService = objectiveCommentService;
    }

    @PostMapping("/set-objective")
    public  String setObjective(EvaluationPeriod evaluationPeriod, Model model) {
       // return "Objective set to: " + objective;

        String employeeNumber = evaluationPeriod.getEmployeeNumber();
        Employee employee = employeeService.getOneEmployee(employeeNumber);
        EvaluationPeriod savedEvaluationPeriod =
                evaluationPeriodService.addEvaluationPeriod(evaluationPeriod);
        model.addAttribute("employee", employee);
        model.addAttribute("evaluationPeriod", savedEvaluationPeriod);

        // get objectives for this employee using the evaluationId
        List<Objective> objectives =
                objectiveService.getObjectivesByEvaluationId(savedEvaluationPeriod.getId(), employeeNumber);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",objectives);
        model.addAttribute("objective", new Objective());
        return  "emp-objective-set";
    }


    @PostMapping("/add-objective")
    public String addObjective(Objective objective, Model model){


        logger.info("Adding Objective: " + objective);

        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());

        //current LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        objective.setSetOn(now.toLocalDate());

        //set the employee number of the currently logged user
        objective.setSetBy(systemUser.getEmployeeNumber());
        objectiveService.createObjective(objective);
        Employee employee = employeeService.getOneEmployee(objective.getEmployeeNumber());
        EvaluationPeriod evaluationPeriod =
                evaluationPeriodService.getEvaluationPeriod(objective.getEvaluationPeriodId());
        List<Objective> objectives =
                objectiveService.getObjectivesByEvaluationId(evaluationPeriod.getId(), employee.getEmployeeNumber());
        model.addAttribute("employee", employee);
        model.addAttribute("evaluationPeriod", evaluationPeriod);
        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("objectives",objectives);


        logger.info("Objectives for this employee: " + objectives);
        return  "emp-objective-set";

    }


    @GetMapping("/objectives-view")
    public  String getObjectiveListView(Model model){



        logger.info("getting employees objectives view");
        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());
        List<EvaluationPeriod> evaluationPeriods = evaluationPeriodService
                .getEvaluationPeriodsForEmployee(systemUser.getEmployeeNumber());
        model.addAttribute("evaluationPeriod", new EvaluationPeriod());
        model.addAttribute("loggedInUser",loggedInUser );
        model.addAttribute("evaluationPeriods",evaluationPeriods );

        return "employee-objectives-view";

    }


    @PostMapping("/get-objectives-for-period")
    public  String objectivesView(Model model, EvaluationPeriod evaluationPeriod){

        EvaluationPeriod theEvaluationPeriod = evaluationPeriodService.getEvaluationPeriod(evaluationPeriod.getId());
        logger.info("getting objectives for evaluation period "+ theEvaluationPeriod);
        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());
        List<Objective> objectiveList = objectiveService.getObjectivesByEvaluationId(theEvaluationPeriod.getId());
        Employee employee = employeeService.getOneEmployee(systemUser.getEmployeeNumber());
        model.addAttribute("employee", employee);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("objectives", objectiveList);
        model.addAttribute("evaluationPeriod", theEvaluationPeriod);
        return  "employee-objectives-view-period";
    }

    @GetMapping("/objective-setting")
    public  String getObjectiveSettingPage(Model model) {


        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();

        List<Employee> surbodinates =  employeeService.getSubordinates(employeeNumber);

        logger.info("subordinates for "+ employeeNumber +" Are "+surbodinates);

        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employees",surbodinates);
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("evaluationPeriod", new EvaluationPeriod());
        return "objective-setting";
    }


    @GetMapping("/objective-update/{id}")
    public String updateObjectivePage(Model model, @PathVariable(name = "id") Long id){

        Objective objective= objectiveService.geObjectiveById(id);
        LoggedInUser loggedInUser = getLoggedInUser();

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("objective", objective);
        model.addAttribute("objectiveComment", new ObjectiveComment());
        return  "objective-emp-update";

    }

    @GetMapping("/objective-emp-view/{id}")
    public String empViewObjective(Model model, @PathVariable(name = "id") Long id){

        Objective objective= objectiveService.geObjectiveById(id);
        LoggedInUser loggedInUser = getLoggedInUser();

        List<ObjectiveComment> objectiveComments =
                objectiveCommentService.getObjectiveComments(objective.getId());
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("objective", objective);
        model.addAttribute("objectiveComments", objectiveComments);
        model.addAttribute("objectiveComment", new ObjectiveComment());
        return  "employee-objective-view";

    }



    @GetMapping("/self-employee-review")
    public  String getSelfReviewInitPage(Model model){

        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());
        List<EvaluationPeriod> evaluationPeriods = evaluationPeriodService
                .getEvaluationPeriodsForEmployee(systemUser.getEmployeeNumber());

        model.addAttribute("evaluationPeriods", evaluationPeriods);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("evaluationPeriod", new EvaluationPeriod());

        logger.info("model has "+loggedInUser + " eval "+ evaluationPeriods);
        return  "self-review-init";
    }


    private LoggedInUser getLoggedInUser(){

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails)principal;
        String userRole  = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        return new LoggedInUser(userDetails.getUsername(),userRole);

    }

}
