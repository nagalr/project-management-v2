package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    // 'ver' will take the version value from application.properties
    @Value("${version}")
    private String ver;

    @Autowired
    IProjectRepository proRepo;

    @Autowired
    IEmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("appVersion", ver);

        // querying the DB for Projects
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);

        List<ChartData> projectData = proRepo.getProjectStatus();

        // convert a projectData object to Json for Javascript use
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // from this line, we expect to receive a Json in the form:
        // [ ["NONSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1] ]

        // sending the Json to the view with the model
        model.addAttribute("projectStatusCnt", jsonString);

        // querying the DB for Employees Projects Count
        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesListProjectCnt", employeesProjectCnt);

        return "main/home";
    }
}
