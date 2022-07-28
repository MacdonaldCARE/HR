package com.tmgreyhat.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        //ROLE_HRMANAGER, ROLE_BUDGETHOLDER, ROLE_COUNTRYDIRECTOR, ROLE_ASSISTANTCOUNTRYDIRECTOR
        http.authorizeRequests()
                .antMatchers("/employees").hasAnyRole("ADMIN", "ACD", "CD", "ACD", "GEN", "HRM")
                .antMatchers("/appli**").hasAnyRole("ADMIN", "ACD", "CD", "ACD", "GEN", "HRM")
                .antMatchers("/").hasAnyRole("ADMIN", "ACD", "CD", "ACD", "GEN", "HRM")
               /* .antMatchers("/").permitAll()*/

                .and().formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/");
       // ROLE_CD, ROLE_ACD, ROLE_BH, ROLE_HRM, ROLE_ADMIN, ROLE_GEN

    }

   /* @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
