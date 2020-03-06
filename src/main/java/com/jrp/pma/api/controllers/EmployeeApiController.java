package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    IEmployeeRepository empRepo;

    @GetMapping
    public List<Employee> getEmployees() {
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return empRepo.findById(id).get();
    }

    /*
     this method will expect datatype: application/json
     the second annotation will return a status code 201 (with CREATED)
     the function will receive a Json format and will save it to the DB
     empRepo.save(employee) - by default return the Employee Object
    */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return empRepo.save(employee);
    }
}
