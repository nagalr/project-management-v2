package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     the second annotation will return a status code 201 (after adding 'CREATED')
     the function will receive a Json format and will save it to the DB
     empRepo.save(employee) - by default return the Employee Object
    */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        return empRepo.save(employee);
    }

    /*
     this method can be void, here we choose to return the Employee
     the default 'consumes' is a Json, we can add that or not
     here, we choose to add that
     the HttpStatus.OK is the '200'
     Here, we are not doing nothing with the path-variable Id
     So we can delete this argument from @PutMapping
     We will leave it, however, for practice
    */
    @PutMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee) {
        return empRepo.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Employee patchEmployee) {
        Employee emp = empRepo.findById(id).get();

        // here we checking what was updated in the PATCH
        // the first check is there was an update that made to the email
        // we checking that by checking if it's equals to null
        // and if an update was done, we will mae the changes
        if (patchEmployee.getEmail() != null) { // if the email included in the request body
            emp.setEmail(patchEmployee.getEmail()); // get the email from the request body and assign it
        }

        if (patchEmployee.getFirstName() != null) { // if the email included in the request body
            emp.setFirstName(patchEmployee.getFirstName()); // get the email from the request body and assign it
        }

        if (patchEmployee.getLastName() != null) { // if the email included in the request body
            emp.setLastName(patchEmployee.getLastName()); // get the email from the request body and assign it
        }

        // saving the updated employee to the DB
        // the 'save' function will return Employee as needed by the function
        return empRepo.save(emp);

    }

    /*
     here we choose to have a different StatusCode - 'NO_CONTENT' (204)
     the 'try-catch' here is for cases that we do not have that id
     in that case, the app will continue to run without crash (but with error)
     but will not delete something that was already deleted
    */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            empRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            //e.printStackTrace(); // this line will give the error, we can leave it empty
        }
    }


}
