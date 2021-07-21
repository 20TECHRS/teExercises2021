package com.techelevator;

public class Employee {


    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String hireDate;
    private Department department;
    private double salary;

    private static double BASE_SALARY = 60000;


    public Employee(long employeeId, String firstName, String lastName, String email, Department department, String hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = BASE_SALARY;
    }

    public Employee() {
        this.employeeId = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.hireDate = "";
        this.salary = BASE_SALARY;
        this.department = null;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static double getBaseSalary() {
        return BASE_SALARY;
    }

    public static void setBaseSalary(double baseSalary) {
        BASE_SALARY = baseSalary;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public double raiseSalary(double percent) {
        return salary = (percent/ 100) * salary + salary;
    }
}
