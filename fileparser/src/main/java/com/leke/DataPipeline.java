package com.leke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;

public class DataPipeline {
    ArrayList<Employee> employeeList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public DataPipeline(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    private void prompt(){
        System.out.println("""
            [1] Filter
            [0] Quit
        """);
    }

    //
    private void operation(int op){
        switch(op){

            //Quit
            case 0 -> {
                System.out.println("Goodbye!");
                System.exit(0);
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
        
        int filterOp = scanner.nextInt();
        switch (filterOp) {
            case 0 -> {}
            case 1 -> {
                System.out.println("Would you like ACTIVE or INACTIVE employees?");
                scanner.nextLine();
                String empAns = scanner.nextLine();
                
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
        while(true){
            prompt();
            int opNum = scanner.nextInt();
            operation(opNum);
        }
    }
}
