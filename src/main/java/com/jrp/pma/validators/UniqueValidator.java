package com.jrp.pma.validators;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// we choose String type here since we want to validate Email
public class UniqueValidator implements ConstraintValidator<IUniqueValue, String> {

    @Autowired
    IEmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // a notice to the console that we entered the method
        System.out.println("Enter validation method....");

        // 'findByEmail' is our implementation in the IEmployeeRepository Interface
        Employee emp = empRepo.findByEmail(value);

        // if emp was populated
        if(emp != null)
            return false;
        else return true;

    }
}
