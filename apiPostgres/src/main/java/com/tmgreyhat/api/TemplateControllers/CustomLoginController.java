package com.tmgreyhat.api.TemplateControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomLoginController {

    @GetMapping("/customLogin")
    public String loginPage() {
        return "login";
    }
}
