package com.tmgreyhat.api.TemplateControllers.objectiveController;

import com.tmgreyhat.api.User.LoggedInUser;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class HRObjectiveController {

    Logger logger = Logger.getLogger(HRObjectiveController.class.getName());

    private final EmployeeService employeeService;
    private final EvaluationPeriodService evaluationPeriodService;
    private final ObjectiveCommentService objectiveCommentService;
    private  final ObjectiveService objectiveService;
    public HRObjectiveController(EmployeeService employeeService,
                                 EvaluationPeriodService evaluationPeriodService,
                                 ObjectiveCommentService objectiveCommentService,
                                 ObjectiveService objectiveService) {
        this.employeeService = employeeService;
        this.evaluationPeriodService = evaluationPeriodService;
        this.objectiveCommentService = objectiveCommentService;
        this.objectiveService = objectiveService;
    }

    @GetMapping("/hr-employee-review")
    public String getEmployeeReviewPage(Model model){

        model.addAttribute("loggedInUser",getLoggedInUser());

        model.addAttribute("employeeList", employeeService.getAllEmployees() );
        return "hr-employee-review-list";
    }

    @GetMapping("/hr-employee-review-init/{id}")
    public  String getReviewInitPage(Model model, @PathVariable(name = "id") String id ){

        Employee employee = employeeService.getOneEmployee(id);
        List<EvaluationPeriod> evaluationPeriods = evaluationPeriodService
                .getEvaluationPeriodsForEmployee(employee.getEmployeeNumber());

        logger.info("LOGGED IN USER"+ getLoggedInUser());
        model.addAttribute("evaluationPeriod", new EvaluationPeriod());
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("employee", employee);
        model.addAttribute("evaluationPeriods", evaluationPeriods);
        return "hr-review-init";
    }

    @GetMapping("/hr-employee-review-for")
    public  String getReview(Model model, EvaluationPeriod evaluationPeriod){

        EvaluationPeriod theEvaluationPeriod =evaluationPeriodService.getEvaluationPeriod(evaluationPeriod.getId());

        LoggedInUser loggedInUser = getLoggedInUser();

        Employee employee = employeeService.getOneEmployee(theEvaluationPeriod.getEmployeeNumber());

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

        int totalObjectives = objectiveList.size();
        OptionalDouble averageRating = employeeReviewEntryList.stream().mapToDouble(EmployeeReviewEntry::getEmployeeRating).average();

        ObjectiveRating rating = new ObjectiveRating();
        rating.setTotal(totalObjectives);
        rating.setAverage(averageRating.isPresent()? averageRating.getAsDouble() : 0 );

        logger.info("EVAL PERIOD "+ theEvaluationPeriod);
        model.addAttribute("employee", employee);
        model.addAttribute("rating", rating);
        model.addAttribute("employeeReviews", employeeReviewEntryList);
        model.addAttribute("evaluationPeriod", theEvaluationPeriod);
        model.addAttribute("loggedInUser", loggedInUser);
        return "self-review-page";
    }
    

}
