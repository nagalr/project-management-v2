package com.jrp.pma.dao;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
 A mechanism to access data in the DB
 refer to as a 'CRUD Repository'
 'Long' is the type of 'projectID' field
 here, it is the second argument, refer to as 'id'
 the first argument is the entity class
 used to access data from the the DB
*/
public interface IProjectRepository extends CrudRepository<Project, Long> {

    /*
     Override the default 'findAll()' that
     returns iterable, and we need to get
     a List in the HomeController 'displayHome' function
    */
    @Override
    List<Project> findAll();

    @Query( nativeQuery = true,
            value = "SELECT stage as label, count(*) as value " +
                    "FROM PROJECT " +
                    "GROUP BY  stage" )
    List<ChartData> getProjectStatus();
}
