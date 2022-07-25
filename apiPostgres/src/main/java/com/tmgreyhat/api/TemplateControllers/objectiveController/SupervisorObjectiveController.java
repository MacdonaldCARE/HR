package com.tmgreyhat.api.TemplateControllers.objectiveController;

import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.Employee;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.evaluation.EvaluationPeriod;
import com.tmgreyhat.api.evaluation.EvaluationPeriodService;
import com.tmgreyhat.api.objective.EmployeeReviewEntry;
import com.tmgreyhat.api.objective.Objective;
import com.tmgreyhat.api.objective.ObjectiveRating;
import com.tmgreyhat.api.objective.ObjectiveService;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveComment;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveCommentService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class SupervisorObjectiveController {

    Logger logger = Logger.getLogger(SupervisorObjectiveController.class.getName());

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final PositionService positionService;
    private final UserService userService;
    private  final ObjectiveService objectiveService;
    private final EvaluationPeriodService evaluationPeriodService;
    private final ObjectiveCommentService objectiveCommentService;

    public SupervisorObjectiveController(EmployeeService employeeService,
                                         ProjectService projectService,
                                         PositionService positionService,
                                         UserService userService,
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


    @GetMapping("/set-objective")
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
        objective.setStatus("CREATED");
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


        return  "redirect:/set-objective?employeeNumber="+
                employee.getEmployeeNumber()+
                "&year="+evaluationPeriod.getYear()+
                "&quarter="+evaluationPeriod.getQuarter();

    }
    @GetMapping("/supervisor-employee-review")
    public  String supervisorViewObjectives(Model model, EvaluationPeriod evaluationPeriod){


       Optional <EvaluationPeriod> theEvaluationPeriod =
                evaluationPeriodService.getEvaluationPeriod(evaluationPeriod.getEmployeeNumber(),
                        evaluationPeriod.getYear(), evaluationPeriod.getQuarter());
        LoggedInUser loggedInUser = getLoggedInUser();

        Employee employee = employeeService.getOneEmployee(evaluationPeriod.getEmployeeNumber());
        model.addAttribute("loggedInUser", loggedInUser);
       if(!theEvaluationPeriod.isPresent()){
           model.addAttribute("errorMsg", "No Objective found for  "+
                   employee.getFullName()+" for "+evaluationPeriod.getYear()+"" +
                   " " +evaluationPeriod.getQuarter());
           return "error-page";

       }




        List<Objective> objectiveList = objectiveService.getObjectivesByEvaluationId(theEvaluationPeriod.get().getId());
        List<EmployeeReviewEntry> employeeReviewEntryList = new ArrayList<>();

        objectiveList.forEach((objective)->{
            EmployeeReviewEntry employeeReviewEntry = new EmployeeReviewEntry();
            employeeReviewEntry.setObjective(objective.getObjectiveTitle());
            employeeReviewEntry.setIndicator(objective.getIndicator());
            ObjectiveComment objectiveComment = new ObjectiveComment();

                if(!objectiveCommentService.getObjectiveComments(objective.getId()).isEmpty()){
                    objectiveComment= objectiveCommentService.getObjectiveComments(objective.getId()).get(0);
                }
            employeeReviewEntry.setObjectiveId(objective.getId());
            employeeReviewEntry.setEmployeeRating(objectiveComment.getEmployeeRating());
            employeeReviewEntry.setEmployeeComment(objectiveComment.getEmployeeComment());
            employeeReviewEntry.setSupervisorRating(objectiveComment.getSupervisorRating());
            employeeReviewEntry.setSupervisorComment(objectiveComment.getSupervisorComment());
            employeeReviewEntry.setEvaluationPeriod(evaluationPeriod.getYear()+ " "+ evaluationPeriod.getQuarter());
            logger.info("ONE ENTRY "+ employeeReviewEntry);
            employeeReviewEntryList.add(employeeReviewEntry);
        });

        int totalObjectives = objectiveList.size();
        OptionalDouble averageRating = employeeReviewEntryList.stream().mapToDouble(EmployeeReviewEntry::getEmployeeRating).average();

        ObjectiveRating rating = new ObjectiveRating();
        rating.setTotal(totalObjectives);
        rating.setAverage(averageRating.isPresent()? averageRating.getAsDouble() : 0 );

        logger.info("Evaluation period"+ theEvaluationPeriod);
        logger.info("Objectives list "+ objectiveList);
        logger.info("EMPLOYEE REVIEWS "+ employeeReviewEntryList);
        model.addAttribute("employee", employee);
        model.addAttribute("evaluationPeriod", theEvaluationPeriod.get());
        model.addAttribute("rating", rating);
        model.addAttribute("employeeReviews", employeeReviewEntryList);

        return "supervisor-review-page";
    }

    @GetMapping("/supervisor-update/{id}")
    public String  supervisorObjectiveUpdatePage(Model model, @PathVariable(name = "id") Long id){


        Objective objective= objectiveService.getObjectiveById(id);
        LoggedInUser loggedInUser = getLoggedInUser();

        ObjectiveComment objectiveComment= new ObjectiveComment();
        if (!objectiveCommentService.getObjectiveComments(id).isEmpty()){
             objectiveComment=  objectiveCommentService.getObjectiveComments(id).get(0);

        }
       logger.info("current objective"+ objectiveComment);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("objective", objective);
        model.addAttribute("currentObjectiveComment", objectiveComment);
        model.addAttribute("objectiveComment", new ObjectiveComment());

        return  "objective-supervisor-update";
    }

    @PostMapping("/supervisor-add-comment")
    public String supervisorAddComment(Model model, ObjectiveComment objectiveComment){
        Long evaluationPeriodId = objectiveService.getEvaluationId(objectiveComment.getObjectiveId());
        logger.info("Updating  "+ objectiveComment);
        objectiveCommentService.supervisorUpdate(objectiveComment);
        EvaluationPeriod theEvaluationPeriod = evaluationPeriodService.getEvaluationPeriod(evaluationPeriodId);
        model.addAttribute("loggedInUser", getLoggedInUser());
        return  "redirect:/supervisor-employee-review?employeeNumber="
                +theEvaluationPeriod.getEmployeeNumber()
                +"&year="+theEvaluationPeriod.getYear()
                +"&quarter="+theEvaluationPeriod.getQuarter();
    }


    @GetMapping("/supervisor-review-init")
    public  String supervisorReviewInit(Model model){

        String username = getLoggedInUser().getUsername();

        String employeeNumber = userService.getUserByUserName(username).getEmployeeNumber();

        List<Employee> subordinates =  employeeService.getSubordinates(employeeNumber);

        logger.info("subordinates for "+ employeeNumber +" Are "+subordinates);

        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employees",subordinates);
        model.addAttribute("evaluationPeriod", new EvaluationPeriod());

        return "supervisor-review-init";
    }
}
