package com.tmgreyhat.api.TemplateControllers.probationControllers;

import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.objective.EmployeeReviewEntry;
import com.tmgreyhat.api.objective.probationComments.ProbationObjectiveComment;
import com.tmgreyhat.api.objective.probationComments.ProbationObjectiveCommentService;
import com.tmgreyhat.api.probation.ProbationPeriod;
import com.tmgreyhat.api.objective.ObjectiveService;
import com.tmgreyhat.api.probation.ProbationPeriodService;
import com.tmgreyhat.api.probationObjective.ProbationObjective;
import com.tmgreyhat.api.probationObjective.ProbationObjectiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class SupervisorProbationController {


    private final UserService userService;
    private final EmployeeService employeeService;
 private  final ProbationPeriodService probationPeriodService;
 private  final ProbationObjectiveService probationObjectiveService;
 private  final ProbationObjectiveCommentService probationObjectiveCommentService;
    private final ObjectiveService objectiveService;


    Logger logger = Logger.getLogger(SupervisorProbationController.class.getName());

    public SupervisorProbationController(UserService userService,
                                         EmployeeService employeeService,
                                         ProbationPeriodService probationPeriodService,
                                         ProbationObjectiveService probationObjectiveService,
                                         ProbationObjectiveCommentService probationObjectiveCommentService,
                                         ObjectiveService objectiveService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.probationPeriodService = probationPeriodService;
        this.probationObjectiveService = probationObjectiveService;
        this.probationObjectiveCommentService = probationObjectiveCommentService;
        this.objectiveService = objectiveService;
    }

    @GetMapping("/probation-setting")
    public String getProbationSetting(Model model) {

        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();

        List<Employee> subordinates =  employeeService.getSubordinates(employeeNumber);

        logger.info("subordinates for "+ employeeNumber +" Are "+subordinates);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employees",subordinates);
        model.addAttribute("ProbationPeriod", new ProbationPeriod());
        return "probation-setting-init";
    }

    @GetMapping("/probation-supervisor-review")
    public String getProbationReviewPage(Model model) {

        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();

        List<Employee> subordinates =  employeeService.getSubordinates(employeeNumber);

        logger.info("subordinates for "+ employeeNumber +" Are "+subordinates);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employees",subordinates);
        model.addAttribute("ProbationPeriod", new ProbationPeriod());
        return "probation-review-init";
    }

    @GetMapping("/supervisor-view-probation-tasks")
    public String getProbationObjectivesListPage(Model model, ProbationPeriod probationPeriod) {


        List<ProbationObjective> objectives =
               probationObjectiveService.getProbationObjectivesForEmployee(probationPeriod.getEmployeeNumber());
        Employee employee = employeeService.getOneEmployee(probationPeriod.getEmployeeNumber());
        if(objectives.isEmpty()){
            model.addAttribute("errorMsg", "No Probation tasks found for  "+
                    employee.getFullName());
            return "error-page";

        }

        List<EmployeeReviewEntry> employeeReviewEntryList = new ArrayList<>();

        logger.info("objectives for "+ employee.getFullName() +" Are "+objectives);
        objectives.forEach(probationObjective -> {

            EmployeeReviewEntry employeeReviewEntry = new EmployeeReviewEntry();

            //use the probationObjective id to fetch probation comments

         Optional <ProbationObjectiveComment> probationObjectiveComment =  probationObjectiveCommentService.getComment(probationObjective.getId());

         if(probationObjectiveComment.isPresent()){
             employeeReviewEntry.setObjective(probationObjective.getObjectiveTitle());
             employeeReviewEntry.setEmployeeComment(probationObjectiveComment.get().getEmployeeComment());
             employeeReviewEntry.setEmployeeRating(probationObjectiveComment.get().getEmployeeRating());
             employeeReviewEntry.setIndicator(probationObjective.getIndicator());
             employeeReviewEntry.setSupervisorComment(probationObjectiveComment.get().getSupervisorComment());
             employeeReviewEntry.setSupervisorRating(probationObjectiveComment.get().getSupervisorRating());
             employeeReviewEntry.setObjectiveId(probationObjectiveComment.get().getId());

             logger.info("employeeReviewEntry for "+ employee.getFullName() +" Are "+employeeReviewEntry);
             employeeReviewEntryList.add(employeeReviewEntry);

         }


        });
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employee",employee);
        model.addAttribute("objectives",employeeReviewEntryList);
        return "supervisor-probation-tasks-view";
    }

    @GetMapping("/set-probation-tasks")
    public String setProbationTasks(Model model, ProbationPeriod probationPeriod) {


        logger.info("probationPeriod is "+probationPeriod+ " for employee "+probationPeriod.getEmployeeNumber());
        String employeeNumber = probationPeriod.getEmployeeNumber();
        Employee employee = employeeService.getOneEmployee(employeeNumber);
        ProbationPeriod savedProbationPeriod =
                probationPeriodService.addProbationPeriod(probationPeriod);
        List<ProbationObjective> objectives = probationObjectiveService.getProbationObjectivesForEmployee(employeeNumber);
        model.addAttribute("employee", employee);
        model.addAttribute("probationPeriod", savedProbationPeriod);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",objectives);

        //ProbationPeriod
        model.addAttribute("objective", new ProbationObjective());
        return "emp-probation-set";
    }

    @PostMapping("/add-probation-objective")
    public String addObjective(ProbationObjective probationObjective, Model model){


        logger.info("Adding Objective: " + probationObjective);

        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());

        //current LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        probationObjective.setSetOn(now.toLocalDate());
        probationObjective.setStatus("CREATED");
        //set the employee number of the currently logged user
        probationObjective.setSetBy(systemUser.getEmployeeNumber());
        probationObjectiveService.addProbationObjective(probationObjective);
        Employee employee = employeeService.getOneEmployee(probationObjective.getEmployeeNumber());
        ProbationPeriod savedProbationPeriod =
                probationPeriodService.getProbationPeriod(probationObjective.getEmployeeNumber());
        List<ProbationObjective> objectives = probationObjectiveService.getProbationObjectivesForEmployee(probationObjective.getEmployeeNumber());


        model.addAttribute("employee", employee);
        model.addAttribute("probationPeriod", savedProbationPeriod);
        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("objectives",objectives);
        logger.info("Objectives for this employee: " + objectives);
        model.addAttribute("objective", new ProbationObjective());


        return  "redirect:/set-probation-tasks?employeeNumber="+
                employee.getEmployeeNumber()+
                "&startDate="+savedProbationPeriod.getStartDate()+
                "&endDate="+savedProbationPeriod.getEndDate();

    }


    @GetMapping("/supervisor-probation-objective-update/{id}")
    public String updateProbationObjective(@PathVariable("id") Long id, Model model){

        logger.info(" well this id "+ id);
        ProbationObjectiveComment probationObjectiveComment = probationObjectiveCommentService.getComment(id).get();
        ProbationObjective probationObjective = probationObjectiveService.getOneObjective(probationObjectiveComment.getId());
        logger.info("probationObjective is "+probationObjective);
        logger.info("probationObjectiveComment is "+probationObjectiveComment);
        Employee employee = employeeService.getOneEmployee(probationObjective.getEmployeeNumber());
        model.addAttribute("objective",probationObjective);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("probationObjectiveComment",probationObjectiveComment);
        model.addAttribute("employee",employee);
        return "probation-objective-sup-update";
    }

    @GetMapping("/supervisor-probation-add-comment")
    public String addComment(Model model, ProbationObjectiveComment probationObjectiveComment){
        logger.info("probationObjectiveComment is "+probationObjectiveComment);

        ProbationObjective probationObjective = probationObjectiveService.getOneObjective(probationObjectiveComment.getObjectiveId());
        Employee employee = employeeService.getOneEmployee(probationObjective.getEmployeeNumber());

        probationObjective.setStatus("SUPERVISOR-UPDATED");

        probationObjectiveService.updateObjective(probationObjective);
        probationObjectiveCommentService.updateComment(probationObjectiveComment);

        model.addAttribute("objective",probationObjective);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("probationObjectiveComment",probationObjectiveComment);
        model.addAttribute("employee",employee);
        return "redirect:/supervisor-view-probation-tasks?employeeNumber="+employee.getEmployeeNumber();
    }

}
