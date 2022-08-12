package com.tmgreyhat.api.TemplateControllers;

import com.tmgreyhat.api.User.UserService;
import com.tmgreyhat.api.employees.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class systemUsersController {

    private final UserService userService;
    private final EmployeeService employeeService;

    public systemUsersController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/system-user-list")
    public String systemUserList(Model model){

        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("loggedInUser",getLoggedInUser());
        return "system-user-list";
    }

    @GetMapping("/system-user-view/{id}")
    public  String userDetailsView(Model model, @PathVariable(name = "id") String id){

        model.addAttribute("user",userService.getByEmployeeNumber(id));
        model.addAttribute("employee",employeeService.getOneEmployee(id));
        model.addAttribute("loggedInUser",getLoggedInUser());
        return "system-user-view";
    }


}
