package com.leke.Operations;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;

public class TransformOperation {
    ArrayList<Employee> employeeList;
    private static final Scanner scanner = new Scanner(System.in);
    
    public TransformOperation(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    public Object transform(boolean skipPrompt, int requestedOp){
        int transformOp = 0;
        if(!skipPrompt){
            System.out.println("""
                What transformation operation would you like to do?

                [1] Extract names
                [2] Extract emails
                [3] Extract salaries
                [0] Go Back
            """);
            
            transformOp = scanner.nextInt();
        }
        return switch (transformOp) {
            //Go back
            case 0 -> { yield null; }

            //Get all employee names
            case 1 -> {
                if(!skipPrompt){
                    System.out.println("-------------List of Employee names-------------");
                    employeeNameExtractor().forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeNameExtractor();
                }
                
            }

            //Get all employee emails
            case 2 -> {
                if(!skipPrompt){
                    System.out.println("-------------List of Employee names-------------");
                    employeeEmailExtractor().forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeEmailExtractor();
                }
            }

            //Get all employee salaries
            case 3 -> {
                if(!skipPrompt){
                    System.out.println("-------------List of Employee names-------------");
                    employeeSalaryExtractor().forEach((e) -> {
                        System.out.println("$" + e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeSalaryExtractor();
                }
            }

            default ->{
                System.out.println("Invalid operation, please try again...");
                yield null;
            }
        };
    }

    private ArrayList<String> employeeNameExtractor(){
        return this.employeeList
            .stream()
            .map((e) -> e.getFirstName() + " " + e.getLastName())
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<String> employeeEmailExtractor(){
        return this.employeeList
            .stream()
            .map(Employee::getEmail)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Integer> employeeSalaryExtractor(){
        return this.employeeList
            .stream()
            .map(Employee::getSalary)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
