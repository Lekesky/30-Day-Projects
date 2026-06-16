package com.leke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;

public class DataPipeline {
    ArrayList<Employee> employeeList = new ArrayList<>();

    public DataPipeline(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    private void prompt(){
        System.out.println("""
                [1] Filter
                [0] Quit
                """);
    }

    private void operation(int op){
        switch(op){

            //Quit
            case 0 -> {
                System.out.println("Goodbye!");
                return;
            }

            //Filter operation
            case 1 -> {
                filter();
            }
            
            default -> {
                System.out.println("Invalid operation, please try again...");
            }
        }
    }

    private void filter(){
        Scanner inputFilter = new Scanner(System.in);
        System.out.println("""
            What filter operation would you like to do?

            [1] Filter by employee status
            [2] Filter by department
            [3] Filter by age
            [4] Filter by hire date
            [5] Filter by job title
            [6] Filter by salary
            [0] Go Back
        """);

        int filterOp = inputFilter.nextInt();
        switch (filterOp) {
            case 0 -> {
                return;
            }
            case 1 -> {
                Scanner empInput = new Scanner(System.in);
                System.out.println("Would you like ACTIVE or INACTIVE employees?");
                String empAns = empInput.nextLine();
                ArrayList<Employee> filteredEmployeesList = employeeFilter(empAns);
                filteredEmployeesList.forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });

            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
            case 5 -> {

            }
            case 6 -> {

            }
            default -> {
                System.out.println("Invalid operation, please try again...");
            }
        }
    }

    private ArrayList<Employee> employeeFilter(String filter){
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        switch(filter.toUpperCase()){
            case "INACTIVE" -> {
                filteredEmployees = this.employeeList.stream()
                .filter(
                    (e) -> e.getStatus().toString().equals("INACTIVE")
                )
                .collect(Collectors.toCollection(ArrayList::new));
                
            }
            case "ACTIVE" -> {
                filteredEmployees = this.employeeList.stream()
                .filter(
                    (e) -> e.getStatus().toString().equals("ACTIVE")
                )
                .collect(Collectors.toCollection(ArrayList::new));
            }
            default -> {

            }
        }
        return filteredEmployees;
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        while(true){
            prompt();
            int op = in.nextInt();
            operation(op);
            in.close();
        }
        
    }


}
