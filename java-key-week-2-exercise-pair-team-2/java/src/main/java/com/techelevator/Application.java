package com.techelevator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static List<Department> departments = new ArrayList();
    public static List<Employee> employees = new ArrayList();
    public static List<Employee> depMarketing = new ArrayList();
    public static List<Employee> deptSales = new ArrayList();
    public static List<Employee> deptEngineering = new ArrayList();
    public static Map<String, Project> projects = new HashMap();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {

        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();

    }

    /**
     * Create departments and add them to the collection of departments
     */
    private static void createDepartments() {

        Department depMarketing = new Department(001, "Marketing");
        departments.add(depMarketing);

        Department depSales = new Department(002, "Sales");
        departments.add(depSales);

        Department depEngineering = new Department (003, "Engineering");
        departments.add(depEngineering);

    }

    /**
     * Print out each department in the collection.
     */
    private static void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");

    }

    /**
     * Create employees and add them to the collection of employees
     */
    private static void createEmployees() {

    }

    /**
     * Print out each employee in the collection.
     */
    private static void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");

    }

    /**
     * Create the 'TEams' project.
     */
    private static void createTeamsProject() {

    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private static void createLandingPageProject() {

    }

    /**
     * Print out each project in the collection.
     */
    private static void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");

    }

}
