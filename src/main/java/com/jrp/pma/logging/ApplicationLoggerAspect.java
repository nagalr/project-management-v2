package com.jrp.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


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
    // A JointPoint Object gives us access to points during the execution of the app
    @Around("definePackagePointcuts()" )
    public Object logAfter(ProceedingJoinPoint jp) {
        log.debug("\n \n \n");
        log.debug("********* Before Method Execution ********** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug("_______________________________________ \n \n \n");

        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        log.debug("********* After Method Execution ********** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug("_______________________________________ \n \n \n");

        return o;

    }





}
