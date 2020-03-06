package com.jrp.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

// This Entity class structure will be mapped to a DB
// It is a mapping between the Java world and the DB world
// Each row in the DB will have the columns: ID, name, stage, description
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator",sequenceName="project_seq", allocationSize=1,initialValue=1)
    private long Project_id;

    private String name;
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
    private String description;

    @ManyToMany(cascade = { CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST },
                                fetch = FetchType.LAZY )
    @JoinTable(name = "project_employee",
               joinColumns = @JoinColumn(name = "project_id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id")
              )

    @JsonIgnore
    private List<Employee> employees;

    public Project() {}

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getProject_id() {
        return Project_id;
    }

    public void setProject_id(long project_id) {
        Project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
