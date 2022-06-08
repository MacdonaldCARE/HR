package com.tmgreyhat.api.TemplateControllers;


import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.User.User;
import com.tmgreyhat.api.applicationToRecruit.ApplicationToRecruit;
import com.tmgreyhat.api.applicationToRecruit.ApplicationToRecruitService;
import com.tmgreyhat.api.departments.DepartmentService;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.fundCode.FundCodeService;
import com.tmgreyhat.api.jobGrade.JobGradeService;
import com.tmgreyhat.api.jobTitles.JobTitleService;
import com.tmgreyhat.api.office.OfficeLocationService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Controller

public class TemplateController {


    Logger logger = Logger.getLogger(TemplateController.class.getName());

    private final  EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobTitleService jobTitleService;
    private final ProjectService projectService;
    private final JobGradeService jobGradeService;
    private final OfficeLocationService officeLocationService;
    private final PositionService positionService;
    private  final ApplicationToRecruitService applicationToRecruitService;
    private final FundCodeService fundCodeService;
    public TemplateController(EmployeeService employeeService,
                              DepartmentService departmentService,
                              JobTitleService jobTitleService,
                              ProjectService projectService,
                              JobGradeService jobGradeService,
                              OfficeLocationService officeLocationService,
                              PositionService positionService,
                              ApplicationToRecruitService applicationToRecruitService,
                              FundCodeService fundCodeService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobTitleService = jobTitleService;
        this.projectService = projectService;
        this.jobGradeService = jobGradeService;
        this.officeLocationService = officeLocationService;
        this.positionService = positionService;
        this.applicationToRecruitService = applicationToRecruitService;
        this.fundCodeService = fundCodeService;
    }

    @GetMapping("login")
    public String getLoginPage(){

        return "login";
    }

    @GetMapping("/")
    public String getMainPage(Model model){

        logger.info("Some page test what what ");
        logger.info("Getting index page"+ getLoggedInUser());
        model.addAttribute("loggedInUser",getLoggedInUser());
        return  "index";
    }




    @GetMapping("/application-view/{id}")
    public  String viewOneApplication(Model model, @PathVariable(name = "id") Integer id){

        if(checkIfAuth()){
            return  "login";
        }
        ApplicationToRecruit application = applicationToRecruitService.getOneApplication(id);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("application",application );
        return "application-view";
    }
    @GetMapping("/application-edit/{id}")
    public  String editOneApplication(Model model, @PathVariable(name = "id") Integer id){
        if(checkIfAuth()){
            return  "login";
        }

        ApplicationToRecruit application = applicationToRecruitService.getOneApplication(id);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("application",application );
        return "application-edit";
    }

    @GetMapping("/application-review/{id}")
    public  String reviewOneApplication(Model model, @PathVariable(name = "id") Integer id){
        if(checkIfAuth()){
            return  "login";
        }

        ApplicationToRecruit application = applicationToRecruitService.getOneApplication(id);
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("application",application );
        return "application-view";
    }

    @PostMapping("/app-recruit-modify")
    public  String updateApplicationToRecruit(Model model, @ModelAttribute ApplicationToRecruit application){
        if(checkIfAuth()){
            return  "login";
        }

        ApplicationToRecruit existingApplication = applicationToRecruitService.getOneApplication(application.getId());
        logger.info(" putting "+ applicationToRecruitService.getOneApplication(application.getId()));
        existingApplication.setStatus(application.getStatus());
        logger.info("APPLICATION "+ existingApplication);

        LoggedInUser loggedInUser = getLoggedInUser();
        model.addAttribute("loggedInUser",loggedInUser);



        //we only changing the status soooo

        //ApplicationToRecruit existingApplication = applicationToRecruitService.getOneApplication(application.getId());

        applicationToRecruitService.createApplicationToRecruit(existingApplication);
        //ROLE_HRMANAGER, ROLE_BUDGETHOLDER, ROLE_COUNTRYDIRECTOR, ROLE_ASSISTANTCOUNTRYDIRECTOR
        if(Objects.equals(loggedInUser.getRole(), "ROLE_BUDGETHOLDER")){

            return  "redirect:/applications-to-recruit-created";
        }
        return  "redirect:/applications-to-recruit-reviewed";
    }
    @PostMapping("/app-recruit")
    public  String createApplicationToRecruit(Model model, @ModelAttribute  ApplicationToRecruit applicationToRecruit){
        if(checkIfAuth()){
            return  "login";
        }

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        applicationToRecruit.setCreatedBy(username);
        applicationToRecruit.setStatus("CREATE");

        model.addAttribute("loggedInUser",getLoggedInUser());
       ApplicationToRecruit newApplicationToRecruit1 = applicationToRecruitService.createApplicationToRecruit(applicationToRecruit);
        logger.info("User  "+ username + " creating ApplicationToRecruit: " + newApplicationToRecruit1.toString());

        //model.addAttribute("application",applicationToRecruitService.getOneApplication(newApplicationToRecruit1.getId()) );

        return  "redirect:/application-view/"+newApplicationToRecruit1.getId();
    }

    @GetMapping("/application-to-recruit")
    public  String getAppToRecruitPage(Model model){
        if(checkIfAuth()){
            return  "login";
        }

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        logger.info("User  "+ userDetails.getAuthorities() + " getting ApplicationToRecruit page");

        String userRole  = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        LoggedInUser loggedInUser = new LoggedInUser(userDetails.getUsername(),userRole);
        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("locations", officeLocationService.getOfficeLocations());
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("application", new ApplicationToRecruit());
        model.addAttribute("fundCodes", fundCodeService.getAllFundCodes());
        model.addAttribute("jobGrades", jobGradeService.getAllJobGrades());
        model.addAttribute("projects", projectService.getAllProjects());
        return "application-to-recruit";
    }

    @GetMapping("/applications-to-recruit-reviewed")
    public  String getApplicationsToRecruitReviewed(Model model){
        if(checkIfAuth()){
            return  "login";
        }
        model.addAttribute("loggedInUser",getLoggedInUser());

        model.addAttribute("applications", applicationToRecruitService.getApplicationWithStatus("REVIEW"));
        return  "applications-to-recruit";
    }

    @GetMapping("/applications-to-recruit-rejected")
    public  String getApplicationsToRecruitRejected(Model model){
        if(checkIfAuth()){
            return  "login";
        }
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("applications", applicationToRecruitService.getApplicationWithStatus("REJECT"));
        return  "applications-to-recruit";
    }


    @GetMapping("/applications-to-recruit-approved")
    public  String getApplicationsToRecruitApproved(Model model){
        if(checkIfAuth()){
            return  "login";
        }
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("applications", applicationToRecruitService.getApplicationWithStatus("APPROVE"));
        return  "applications-to-recruit";
    }

    @GetMapping("/applications-to-recruit-created")
    public  String getApplicationsToRecruitCreated(Model model){
        if(checkIfAuth()){
            return  "login";
        }
        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("applications", applicationToRecruitService.getApplicationWithStatus("CREATE"));
        return  "applications-to-recruit";
    }

    @GetMapping("/applications-to-recruit")
    public String applicationsToRecruit(Model model) {
        if(checkIfAuth()){
            return  "login";
        }

        model.addAttribute("loggedInUser",getLoggedInUser());
        model.addAttribute("applications", applicationToRecruitService.getAllApplications());
        return  "applications-to-recruit";
    }

    private LoggedInUser getLoggedInUser(){

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails)principal;
        //logger.info("User  "+ userDetails.getAuthorities() + " getting ApplicationToRecruit page");

        String userRole  = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        return new LoggedInUser(userDetails.getUsername(),userRole);

    }


    private boolean checkIfAuth(){

        return  getLoggedInUser().getRole() ==null;
    }


}
