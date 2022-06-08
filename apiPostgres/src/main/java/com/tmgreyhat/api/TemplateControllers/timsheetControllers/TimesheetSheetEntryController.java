package com.tmgreyhat.api.TemplateControllers.timsheetControllers;

import com.tmgreyhat.api.User.LoggedInUser;
import com.tmgreyhat.api.applicationToRecruit.ApplicationToRecruitService;
import com.tmgreyhat.api.departments.DepartmentService;
import com.tmgreyhat.api.employees.EmployeeService;
import com.tmgreyhat.api.fundCode.FundCodeService;
import com.tmgreyhat.api.jobTitles.JobTitleService;
import com.tmgreyhat.api.office.OfficeLocationService;
import com.tmgreyhat.api.position.PositionService;
import com.tmgreyhat.api.project.ProjectService;
import com.tmgreyhat.api.timesheet.TimeSheetEntry;
import com.tmgreyhat.api.timesheet.TimesheetEntryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class TimesheetSheetEntryController {


    Logger logger = Logger.getLogger(TimesheetSheetEntryController.class.getName());
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobTitleService jobTitleService;
    private final ProjectService projectService;
    private final OfficeLocationService officeLocationService;
    private final TimesheetEntryService timesheetEntryService;
    private final PositionService positionService;
    private  final ApplicationToRecruitService applicationToRecruitService;
    private final FundCodeService fundCodeService;

    public TimesheetSheetEntryController(EmployeeService employeeService,
                                         DepartmentService departmentService,
                                         JobTitleService jobTitleService,
                                         ProjectService projectService,
                                         OfficeLocationService officeLocationService,
                                         TimesheetEntryService timesheetEntryService,
                                         PositionService positionService,
                                         ApplicationToRecruitService applicationToRecruitService,
                                         FundCodeService fundCodeService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobTitleService = jobTitleService;
        this.projectService = projectService;
        this.officeLocationService = officeLocationService;
        this.timesheetEntryService = timesheetEntryService;
        this.positionService = positionService;
        this.applicationToRecruitService = applicationToRecruitService;
        this.fundCodeService = fundCodeService;
    }

    @GetMapping("/casual-timesheet")
    public  String getCasualTimesheetPage(Model model){

        model.addAttribute("locations", officeLocationService.getOfficeLocations());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("timesheetEntry", new TimeSheetEntry());

        return "casual-timesheet";

    }



    @PostMapping("/timesheet-add")
    public String recordTimeSheetEntry (Model model, TimeSheetEntry timesheetEntry){

        logger.info("Recording Timesheet Entry: " + timesheetEntry);

        timesheetEntryService.createTimeSheetEntry(timesheetEntry);


        return "redirect:/casual-timesheet";
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
