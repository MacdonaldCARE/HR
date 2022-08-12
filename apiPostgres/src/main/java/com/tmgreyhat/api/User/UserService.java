package com.tmgreyhat.api.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {


    Logger logger = Logger.getLogger(UserService.class.getName());
    private final PasswordEncoder passwordEncoder;
    private final  UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public  User registerNewUser(User userObject){

       boolean userExists = userRepository.
               findByEmployeeNumber(userObject.getEmployeeNumber())
               .isPresent();

       if(userExists){

           logger.info("User already exists"+ userObject.getEmployeeNumber());
           return null;
       }
        User user = userObject;
        user.setPassword(passwordEncoder.encode(userObject.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return  userRepository.findAll();
    }

    public boolean checkIfUserExists(String userName) {

        return  userRepository.findByUserName(userName).isPresent();
    }

    public User getUserByUserName(String username) {

        return  userRepository.findByUserName(username).get();
    }

    public User getByEmployeeNumber(String id) {

        return userRepository.findByEmployeeNumber(id).get();
    }
}
