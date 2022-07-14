package com.tmgreyhat.api.TemplateControllers.securityControllers.passwordController;


import com.tmgreyhat.api.User.LoggedInUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

import static com.tmgreyhat.api.security.LoggedInUserProvider.getLoggedInUser;

@Controller
public class PasswordController {


    Logger logger = Logger.getLogger(PasswordController.class.getName());

    @GetMapping("/change-password")
    public  String getChangePasswordPage(Model model){

        LoggedInUser loggedInUser = getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        return "change-password";
    }
}
