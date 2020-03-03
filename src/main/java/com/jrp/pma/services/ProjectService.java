package com.jrp.pma.services;

import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;
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
public class ProjectService {

    @Autowired
    IProjectRepository proRepo;

    public Project save (Project project) {
        return proRepo.save(project);
    }

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return proRepo.getProjectStatus();
    }
}
