package com.bhanuka.apicallapplication.models;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {
    private String status;
    private List<Employee> employees;

    public APIResponse(String status, List<Employee> employees) {
        this.status = status;
        this.employees = employees;
    }

    public APIResponse(String status) {
        this.status = status;
        employees = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
