package com.jrp.pma.services;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 @Service - so it will add to the Spring Context
 When the application is loaded
 this registered as a Service Bean.
 All we do here is implementing the repository functions
*/
@Service
public class EmployeeService {

    @Autowired
    IEmployeeRepository empRepo;

    public Employee save (Employee employee) {
        return empRepo.save(employee);
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }
}
