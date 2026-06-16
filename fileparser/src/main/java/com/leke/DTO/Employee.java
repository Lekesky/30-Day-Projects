package com.leke.DTO;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String jobTitle;
    private int salary;
    private LocalDate hireDate;
    private Status status;
    private int age;

    public Employee(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return 
        "Id: " + this.id + "\n" +
        "First Name: " + this.getFirstName() + "\n" +
        "Last Name: " + this.getLastName() + "\n" +
        "Email: " + this.getEmail() +  "\n" +
        "Department: " + this.getDepartment() + "\n" +
        "Job Title: " + this.getJobTitle() +  "\n" +
        "Salary: " + this.getSalary() +  "\n" +
        "Hire Date: " + this.getHireDate() +  "\n" +
        "Status: " + this.getStatus() +  "\n" +
        "Age: " + this.getAge();

    }


}
