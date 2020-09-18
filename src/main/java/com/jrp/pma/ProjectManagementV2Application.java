package com.jrp.pma;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectManagementV2Application {

    @Autowired
    IEmployeeRepository empRepo;

    @Autowired
    IProjectRepository projRepo;

    public static void main(String[] args) {

        SpringApplication.run(ProjectManagementV2Application.class, args);
    }
}
