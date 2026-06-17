package com.leke.Operations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;
import com.leke.DTO.Status;

public class GroupByOperation {
    ArrayList<Employee> employeeList;
    private static final Scanner scanner = new Scanner(System.in);

    public GroupByOperation(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    public Object groupBy(boolean skipPrompt, int requestedOp){
        int groupByOp = 0;
        if(!skipPrompt){
            System.out.println("""
                What group by operation would you like to do?

                [1] Group by status
                [2] Group by department
                [3] Head count per department
                [4] Average salary per department
                [5] Total salary payroll
                [6] Top Earners per department
                [0] Go Back
            """);
        
            groupByOp = scanner.nextInt();
        }else{
            groupByOp = requestedOp;
        }
         
        return switch (groupByOp) {
            //Go back
            case 0 -> { yield null; }

            //Group employees by Status
            case 1 -> {
                if(!skipPrompt){
                    employeestatusGroupBy().forEach((k, v) -> {
                        System.out.println("--------- " + k +" group ---------");
                        v.forEach( e -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                        });
                    });
                    yield null;
                }else{
                    yield employeestatusGroupBy();
                }
                
            }

            //Group employees by Department
            case 2 -> {
                if(!skipPrompt){
                    employeeDepartmentGroupBy().forEach((k, v) -> {
                        System.out.println("------------------------ " + k +" group ------------------------");
                        v.forEach( e -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                        });
                    });
                    yield null;
                }else{
                    yield employeeDepartmentGroupBy();
                }
            }

            //Head count on employees per Department
            case 3 -> {
                if(!skipPrompt){
                    employeeHeadCountPerDepartmentGroupBy().forEach((k, v) -> {
                        System.out.println("------------------------ " + k +" group ------------------------");
                        System.out.println("Head count: " + v);
                    });
                    yield null;
                }else{
                    yield employeeHeadCountPerDepartmentGroupBy();
                }
            }

            //Average Salary per Department
            case 4 -> {
                if(!skipPrompt){
                    employeeAverageSalaryPerDeparmentGroupBy().forEach((k, v) -> {
                        System.out.println("------------------------ " + k +" group ------------------------");
                        System.out.println("Average Salary: " + v);
                    });
                    yield null;
                }else{
                    yield employeeAverageSalaryPerDeparmentGroupBy();
                }
            }

            //Total Salary payroll
            case 5 -> {
                if(!skipPrompt){
                    System.out.println("------------------------------------------------");
                    System.out.println("$" + employeeTotalSalaryGroupBy());
                    yield null;
                }else{
                    yield employeeTotalSalaryGroupBy();
                } 
            }

            case 6 ->{
                if(!skipPrompt){
                    employeeTopEarnerPerDepartmentGroupBy().forEach((k, v) -> {
                        System.out.println("------------------------ " + k +" group ------------------------");
                        System.out.println(v.get());
                    });
                    yield null;
                }else{
                    yield employeeTopEarnerPerDepartmentGroupBy();
                }
            }

            default -> {
                System.out.println("");
                yield null;
            }

        };
    }

    private Map<Status, List<Employee>> employeestatusGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getStatus()));
    }

    private Map<String, List<Employee>> employeeDepartmentGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getDepartment()));
    }

    private Map<String, Long> employeeHeadCountPerDepartmentGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getDepartment(), Collectors.counting()));
    }

    private Map<String, Optional<Employee>> employeeTopEarnerPerDepartmentGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getDepartment(), Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
    }

    private Map<String, Double> employeeAverageSalaryPerDeparmentGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getDepartment(), Collectors.averagingInt((e) -> e.getSalary())));

    }

    private int employeeTotalSalaryGroupBy(){
        return this.employeeList.stream().map((e) -> e.getSalary()).reduce(0, (emp1, emp2) -> emp1 + emp2);
    }
}
