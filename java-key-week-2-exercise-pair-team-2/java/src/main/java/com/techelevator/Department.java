package com.techelevator;

public class Department {


    
    // define Department.java class- done ***
    // departmentId equals an int
    // name equals a string
    private int departmentID;
    private String name;

public Department (int Department, String name) {     //Ctor
    this.departmentID = departmentID;
    this.name = name;
}

    public int getDepartmentID() {                   // Getters & Setters
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
