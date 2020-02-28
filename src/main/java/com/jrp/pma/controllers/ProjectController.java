package com.jrp.pma.controllers;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/*
 Controller class to handle the route "/projects" and sub-routes
 The 'Controller' annotation will make any function within the class
 to return a view, in our case, an HTML file in 'templates' folder
 meaning, if a method return "new-project" -'new-project.html' will load
 if the class annotation was @RestController, than if a method
 returns: return "new-projects", this text will display on the page
*/
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    IProjectRepository proRepo;

    @Autowired
    IEmployeeRepository empRepo;

    @GetMapping
    public String projectsList(Model model) {

        // querying the DB for Projects
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);

        return "projects/projects-list";
    }
    /*
     a usage of the 'IProjectRepository' Interface
     '@Autowired' - we leave to Spring to create
     the needed Instance.
     There is no class (it's Interface), so Spring will create
     an anonymous class and inject it into our
     variable - proRepo. using proRepo in "/save"
    */

    /*
     Instead of writing:
     @RequestMapping("/new") - the default is 'GET'
     we write: @GetMapping
    */
    @GetMapping("/new")
    public String displayProjectForm (Model model) {

        Project aProject = new Project();

        List<Employee> employees = empRepo.findAll();

        /*
         Here we map an empty Object to the form. (aProject)
         'project' is the name in the html form, 'aProject' is the Object
         that will take the data from the form. (the view)
         later, will insert the Object data fields into a DB
        */
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    /*
     Instead of writing this:
     @RequestMapping(value = "/save", method = RequestMethod.POST)
     we can use @PostMapping (a shortcut)
     Since the default of @RequestMapping is 'GET'
     and here we need a 'POST'
     The route on the form: <form action="/projects/save"
     will route here.
    */
    @PostMapping("/save")
    public String createProject (Project project,
                                 Model model ) {

        // Saving the 'project' Object to the DB
        proRepo.save(project);

        /*
         Redirect after saving to the DB
         Preventing duplicate submissions
        */
        return "redirect:/projects";
    }
}
