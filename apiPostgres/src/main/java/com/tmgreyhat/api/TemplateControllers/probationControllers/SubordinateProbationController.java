package com.tmgreyhat.api.TemplateControllers.probationControllers;


import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.objective.ObjectiveService;
import com.tmgreyhat.api.objective.probationComments.ProbationObjectiveComment;
import com.tmgreyhat.api.objective.probationComments.ProbationObjectiveCommentService;
import com.tmgreyhat.api.probation.ProbationPeriodService;
import com.tmgreyhat.api.probationObjective.ProbationObjective;
import com.tmgreyhat.api.probationObjective.ProbationObjectiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class SubordinateProbationController {

    Logger logger = Logger.getLogger(SubordinateProbationController.class.getName());

    private final UserService userService;
    private final EmployeeService employeeService;
    private  final ProbationPeriodService probationPeriodService;
    private  final ProbationObjectiveService probationObjectiveService;
    private final ProbationObjectiveCommentService probationObjectiveCommentService;


    public SubordinateProbationController(UserService userService,
                                          EmployeeService employeeService,
                                          ProbationPeriodService probationPeriodService,
                                          ProbationObjectiveService probationObjectiveService,
                                          ObjectiveService objectiveService,
                                          ProbationObjectiveCommentService probationObjectiveCommentService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.probationPeriodService = probationPeriodService;
        this.probationObjectiveService = probationObjectiveService;

        this.probationObjectiveCommentService = probationObjectiveCommentService;
    }


    @GetMapping("/probation-update")
    public String getObjectivesUpdatePage(Model model){
        LoggedInUser loggedInUser = getLoggedInUser();
        User systemUser = userService.getUserByUserName(loggedInUser.getUsername());

       String employeeNUmber= systemUser.getEmployeeNumber();

       List<ProbationObjective> objectiveList =  probationObjectiveService.getProbationObjectivesForEmployee(employeeNUmber);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objectives",objectiveList);
        model.addAttribute("employee",employeeService.getOneEmployee(employeeNUmber));
       return  "employee-probation-tasks-view";

    }

    @GetMapping("/probation-objective-update/{id}")
    public  String getObjectiveUpdatePage(Model model,  @PathVariable(name = "id") Long id){
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("objective",probationObjectiveService.getOneObjective(id));
        model.addAttribute("probationObjectiveComment", new ProbationObjectiveComment());
        return "probation-objective-emp-update";
    }

    @PostMapping("/probation-emp-add-comment")
    public String updateObjective(Model model, ProbationObjectiveComment probationObjectiveComment){

        LoggedInUser loggedInUser = getLoggedInUser();

        ProbationObjective probationObjective = probationObjectiveService.getOneObjective(probationObjectiveComment.getObjectiveId());


        probationObjective.setStatus("EMP-UPDATED");

        probationObjectiveService.updateObjective(probationObjective);
        logger.info(probationObjectiveComment.toString());
        probationObjectiveCommentService.addProbationObjectiveComment(probationObjectiveComment);


        return "redirect:/probation-update";
    }


}
