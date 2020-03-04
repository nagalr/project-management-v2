package com.jrp.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 A configuration class for configuring the WebMvc
 with Encoder, Resources and more configuration associated with WebMvc
*/

/*
 a configuration class, the components scan will pick this Bean up to Spring Context
 Hence, we can @Autowired this class in other class
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean // Define our Encoder here, this one is very popular now
    public BCryptPasswordEncoder passwordEncoder() {

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder; // return the Bean
    }
}
