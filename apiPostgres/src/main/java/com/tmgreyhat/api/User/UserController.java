package com.tmgreyhat.api.User;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){

        return userService.registerNewUser(user);

    }

    @GetMapping
    public List<User> getUserList(){

        return  userService.getAllUsers();
    }
}
