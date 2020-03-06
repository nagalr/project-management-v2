package com.jrp.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_generator")
    @SequenceGenerator(name="employee_generator",sequenceName="employee_seq", allocationSize=1,initialValue=1)
    private long employee_id;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    // The @Email validation will validate everything needed
    // to validate an Email address
    @NotNull
    @Email
    private String email;

    @ManyToMany(cascade = { CascadeType.DETACH,
                           CascadeType.MERGE,
                           CascadeType.REFRESH,
                           CascadeType.PERSIST },
                            fetch = FetchType.LAZY )
    @JoinTable(name = "project_employee",
                joinColumns = @JoinColumn(name = "employee_id"),
                inverseJoinColumns = @JoinColumn(name = "project_id")
              )

    @JsonIgnore
    private List<Project> projects;

    public Employee() {}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
