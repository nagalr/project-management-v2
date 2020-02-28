package com.jrp.pma.services;

import com.jrp.pma.dao.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 A 'dummy' class to practise 3 different
 dependency Injection options into an Object
*/


/*
 Spring 'stereotype' (like @Service) is a must
 for the @Autowired - for the Constructor and Setter
 dependencies to be Injected, and an instance of the class
 will be added to the container. (Spring Context)
*/
@Service
public class EmployeeService {

    /*
     Field Injection - with @Autowired
     will be injected by Spring Framework
    */
    @Autowired
    IEmployeeRepository empRepo;

    /*
     Constructor Injection
     we Don't need here an annotation from
     Spring 'stereotype' to work.
     Meaning, for Spring to scan the class
     and the Object on run time, and create
     the relevant field in Spring-Context
     This is a proper way to create a dependency Injection.
    */
    public EmployeeService(IEmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    /*
     Injection with a Setter function
     here we need the @Autowired annotation
     so Spring will now to Inject a dependency
     of IEmployeeRepository here. (a dependency Injection)
     (there can be few Setters in the class)
    */
    @Autowired
    public void setProRepo(IEmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

}
