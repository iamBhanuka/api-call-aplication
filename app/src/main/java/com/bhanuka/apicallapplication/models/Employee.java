package com.bhanuka.apicallapplication.models;

import androidx.annotation.NonNull;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class Employee {
    private String id;
    private String name;
    private String salary;
    private String age;
    private String image;

    public Employee() {
    }

    public Employee(String id, String name, String salary, String age, String image) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        https://via.placeholder.com/500
        this.image = image;
    }

    public static Employee fromJson(JSONObject jsonObject) throws JSONException {
        Employee employee = new Employee();
        employee.setId(jsonObject.getString("id"));
        employee.setName(jsonObject.getString("employee_name"));
        employee.setSalary(jsonObject.getString("employee_salary"));
        employee.setAge(jsonObject.getString("employee_age"));
        employee.setImage(jsonObject.getString("profile_image"));
        return employee;
    }
}
