package com.jrp.pma.dao;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

// used to access data from the the DB
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    /*
     @Query for querying the DB
     nativeQuery to the used DB,
     not a query particular for Spring JPA
     Thw original query on the DB is:
     SELECT e.first_name, e.last_name, count(pe.employee_id)
        FROM  employee e left join project_employee pe
        ON pe.employee_id = e.employee_id
        GROUP BY e.first_name, e.last_name
        Order by 3 DESC;
    */
    @Query(nativeQuery = true,
            value = "SELECT e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) as projectCount " +
                    "FROM  employee e left join project_employee pe " +
                    "ON pe.employee_id = e.employee_id " +
                    "GROUP BY e.first_name, e.last_name " +
                    "Order by 3 DESC;")
    List<EmployeeProject> employeeProjects();

    // Spring will be smart to understand that we asking for Email
    // we do not need to implement the functionality, just write it correctly
    // meaning, writing: 'findByEmail'
    Employee findByEmail(String value);

    // another example that we can use (but will not)
    // just an example to how Spring-Data is smart to
    // understand what to query the DB for
    // Employee findByemployee_id(long id);
}
