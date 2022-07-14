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
import com.tmgreyhat.api.objective.ObjectiveService;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveComment;
import com.tmgreyhat.api.objective.objectiveComments.ObjectiveCommentService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller

public class EmployeeObjectiveController {
    Logger logger = Logger.getLogger(EmployeeObjectiveController.class.getName());

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final PositionService positionService;
    private final UserService userService;
    private  final ObjectiveService objectiveService;
    private final EvaluationPeriodService evaluationPeriodService;
    private final ObjectiveCommentService objectiveCommentService;
    public EmployeeObjectiveController(EmployeeService employeeService,
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

    @PostMapping("/emp-add-comment")
    public String  updateObjective(Model model, ObjectiveComment objectiveComment){

        LoggedInUser loggedInUser = getLoggedInUser();

        Objective objective = objectiveService.getObjectiveById(objectiveComment.getObjectiveId());
        objective.setStatus("EMP-UPDATE");
        objectiveService.updateObjective(objective);
        objectiveComment.setSubmittedOn(LocalDateTime.now().toLocalDate());
        objectiveCommentService.createObjectiveComment(objectiveComment);


        return  "redirect:/objective-emp-view/"+objectiveComment.getObjectiveId();

    }

    @GetMapping("/self-review-init")
    public String getSelfEvalForPeriod(Model model, EvaluationPeriod evaluationPeriod){

        EvaluationPeriod theEvaluationPeriod =
                evaluationPeriodService.getEvaluationPeriod(evaluationPeriod.getId());
        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());
        Employee employee = employeeService.getOneEmployee(systemUser.getEmployeeNumber());

        //we need to get all the objectives of the current eval period
        // and get all the objective comments of that period

        List<Objective> objectiveList = objectiveService.getObjectivesByEvaluationId(evaluationPeriod.getId());
        List<EmployeeReviewEntry> employeeReviewEntryList = new ArrayList<>();
        objectiveList.forEach((objective)->{
            EmployeeReviewEntry employeeReviewEntry = new EmployeeReviewEntry();
            employeeReviewEntry.setObjective(objective.getObjectiveTitle());
            employeeReviewEntry.setIndicator(objective.getIndicator());
            ObjectiveComment objectiveComment =
                    new ObjectiveComment();

            if(!objectiveCommentService.getObjectiveComments(objective.getId()).isEmpty()){

                objectiveComment=  objectiveCommentService.getObjectiveComments(objective.getId()).get(0);
            }
            //get the comment for each objective and attach
            employeeReviewEntry.setEmployeeRating(objectiveComment.getEmployeeRating());
            employeeReviewEntry.setEmployeeComment(objectiveComment.getEmployeeComment());
            employeeReviewEntry.setSupervisorRating(objectiveComment.getSupervisorRating());
            employeeReviewEntry.setSupervisorComment(objectiveComment.getSupervisorComment());
            employeeReviewEntry.setEvaluationPeriod(evaluationPeriod.getYear()+ " "+ evaluationPeriod.getQuarter());
            employeeReviewEntryList.add(employeeReviewEntry);
        });
        model.addAttribute("employee", employee);
        model.addAttribute("employeeReviews", employeeReviewEntryList);
        model.addAttribute("evaluationPeriod", theEvaluationPeriod);
        model.addAttribute("loggedInUser", loggedInUser);
        return  "self-review-page";
    }
}
