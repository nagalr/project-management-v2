package com.jrp.pma.dto;

// we must define 'get' before the property name
// since Spring-Data will look for 'get'
public interface EmployeeProject {

    public String getFirstName();
    public String getLastName();
    public int getProjectCount();
}
