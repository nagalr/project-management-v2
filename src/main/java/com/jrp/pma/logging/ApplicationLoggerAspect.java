package com.jrp.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// AAspect is a code that we want to run as a cross cutting concern
@Aspect
@Component // we must define @Component so at the scanning it will go to the Spring Context
public class ApplicationLoggerAspect {

    // we define a 'log' variable that we can utilized all over the application
    private final Logger log  = LoggerFactory.getLogger(this.getClass());

    /*
     Define what the log format should be
     one way of logging all over the application
     using @Pointcut we define where the code here should run
    */
    @Pointcut("within(com.jrp.pma.controllers..*)")
    public void definePackagePointcuts() {
        // empty method to name the location specified in the pointcut (is like location here)
    }

    // This method will run after the method in the ()
    @After("definePackagePointcuts()")
    public void log() {
        log.debug("---------------------------------");
    }

}
