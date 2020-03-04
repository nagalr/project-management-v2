package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

// the @Configuration annotation will add the class to Spring Context
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource; // Spring will auto-wired h2 as the data-source (or Postgresql)

    @Autowired // this Bean is defined in our WebConfig class
    BCryptPasswordEncoder bCryptEncoder; // it will inject a new instance here
    /*
     Override the default function to define the Authentication Mechanism
     we will use the type: AuthenticationManagerBuilder to build our rules
     using this, Spring later will build 'USERS' and 'AUTHORITIES' tables
    */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .withDefaultSchema() // will create tables to support our Authentication and Authorizations rules, by creating 'USERS' and 'AUTHORITIES' tables
//                .withUser("myuser")
//                    .password("pass")
//                        .roles("USER")
//                .and()
//                .withUser("taz")
//                    .password("pass2")
//                    .roles("USER")
//                .and()
//                .withUser("managerUser")
//                    .password("pass3")
//                    .roles("ADMIN");
//    }

    /*
     another similar function, different implementation
     This implementation works with the h2-console definition disabled
     (in application-dev.properties)
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled " +
                                      "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role " +
                                            "from user_accounts where username = ?")
                .dataSource(dataSource)  // Spring will Autowired 'dataSource'
                .passwordEncoder(bCryptEncoder); // here decoding the password, when a user registered the password will be Encoded
    }

    // define the Authorization functionality
    // we will specify here what the logged-in user allowed to-do with 'roles'
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN") // Define that only "ADMIN" can access a new Project Creation
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN") // Define that only "ADMIN" can access a new Employee Creation
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/", "/**").permitAll() // if this role was on top of the previous two, only this one will count
                .and()
                .formLogin(); // using the default login form, we can define .loginPage() if we have

        // Disable to access h2-console (with the above 'permitAll()' )
        // http.csrf().disable(); // good only for h2 usage
        // http.headers().frameOptions().disable(); // good only for h2 usage
    }
}
