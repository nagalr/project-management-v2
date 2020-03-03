package com.jrp.pma.logging;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// AAspect is a code that we want to run as a cross cutting concern
@Aspect
public class ApplicationLoggerAspect {

    // we define a 'log' variable that we can utilized all over the application
    private final Logger log  = LoggerFactory.getLogger(this.getClass());

}
