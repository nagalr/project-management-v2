package com.jrp.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

// the @Configuration annotation will add the class to Spring Context
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    // Override the default function to define the Authentication Mechanism
    // we will use the type: AuthenticationManagerBuilder to build our rules
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("myuser")
                    .password("pass")
                        .roles("USER");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

    }

}
