package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

// the @Configuration annotation will add the class to Spring Context
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource; // Spring will auto-wired h2 as the data-source

    // Override the default function to define the Authentication Mechanism
    // we will use the type: AuthenticationManagerBuilder to build our rules
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .withDefaultSchema() // will create tables to support our Authentication and Authorizations rules
                .withUser("myuser")
                    .password("pass")
                        .roles("USER")
                .and()
                .withUser("taz")
                    .password("pass2")
                    .roles("USER")
                .and()
                .withUser("managerUser")
                    .password("pass3")
                    .roles("ADMIN");


    }

    /*
     Define a Bean that will load to Spring Context
     and returns 'PasswordEncoder' Object.
     we will define inside 'NoPassword..' to have some
     functionality of the code, a real Password Encoder
     will be built later, this is a temp option
    */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();

    }

    // define the Authorization functionality
    // we will specify here what the logged-in user allowed to-do with 'roles'
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN") // Define that only "ADMIN" can access a new Project Creation
                .antMatchers("/employees/new").hasRole("ADMIN") // Define that only "ADMIN" can access a new Employee Creation
                .antMatchers("/").authenticated().and().formLogin(); // anyone that is authenticated has access to the endpoint "/"
    }
}
