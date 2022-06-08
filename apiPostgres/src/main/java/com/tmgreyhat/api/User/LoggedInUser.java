package com.tmgreyhat.api.User;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LoggedInUser {
    private String username;
    private String role;
    public  LoggedInUser(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
