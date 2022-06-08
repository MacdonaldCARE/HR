package com.tmgreyhat.api.security;

import com.tmgreyhat.api.User.LoggedInUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInUserProvider {
    public  static LoggedInUser getLoggedInUser(){

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails)principal;
        String userRole  = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        return new LoggedInUser(userDetails.getUsername(),userRole);

    }
}
