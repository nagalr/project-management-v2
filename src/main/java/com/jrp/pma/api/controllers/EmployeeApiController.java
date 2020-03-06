package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
