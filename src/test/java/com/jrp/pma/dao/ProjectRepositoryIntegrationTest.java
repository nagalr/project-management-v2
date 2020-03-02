package com.jrp.pma.dao;

/*
 the test will load all the classes from Spring Context
 That includes all the @Autowired Object and all in the context
 Hence, we need some annotations
 Starting the test with the main app class
 So we give it as a parameter to the class function.
 The second annotation is Spring Runner.
 The Test will use application.properties in the Resources here
*/

import com.jrp.pma.ProjectManagementV2Application;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class) // needed to junit testing
@SqlGroup( {@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}), // by default, it will drop the tables and sequences if exist, before execution of the line, it drops at the end too!
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql") }) // not needed here, just to show another execution phase available at end of executing the previous line, the test will run exactly without this line
public class ProjectRepositoryIntegrationTest {

    // The Project class will get injected into proRepo variable
    @Autowired
    IProjectRepository proRepo;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project newProject = new Project( "New Test Project",
                                          "COMPLETE",
                                         "Test Description");
        proRepo.save(newProject);

        /*
         we expect 5 projects there
         even thought we have only 4 in data.sql, we expect 5
         since we add one here
        */
        assertEquals(5, proRepo.findAll().size());


    }


}
