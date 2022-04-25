package com.tmgreyhat.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.tmgreyhat.api.security.ApplicationUserRole.ADMIN;
import static com.tmgreyhat.api.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/vendor/**", "/js/**").permitAll()
                .antMatchers("/", "index" , "/css/*", "/js/*").permitAll() //permit all users to access the / , index, css, js dirs
                .antMatchers("/api/**").hasRole(ADMIN.name()) // permit only ADMIN role users to access any /api/**** routes
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll();//.loginPage("/login").permitAll();


    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {


        UserDetails rootUser = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .roles(STUDENT.name())
                .build();
        UserDetails adminUser = User.builder()
                .username("tapiwa")
                .password(passwordEncoder.encode("tapiwa"))
                .roles(ADMIN.name())
                .build();

        return  new InMemoryUserDetailsManager(rootUser, adminUser);
    }
}
