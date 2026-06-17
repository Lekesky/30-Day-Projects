package com.leke;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.leke.DTO.Employee;
import com.leke.Operations.FilterOperation;
import com.leke.Operations.GroupByOperation;
import com.leke.Operations.TransformOperation;

public class DataPipeline {
    ArrayList<Employee> employeeList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    GroupByOperation groupByOp;
    TransformOperation transformOp;
    FilterOperation filterOp;

    public DataPipeline(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
        this.groupByOp = new GroupByOperation(this.employeeList);
        this.transformOp = new TransformOperation(this.employeeList);
        this.filterOp = new FilterOperation(this.employeeList);
    }

    private void prompt(){
        System.out.println("""
            [1] Filter
            [2] Transform
            [3] Group By
            [4] Create Full report
            [0] Quit
        """);
    }

    private void operation(int op){
        switch(op){

            //Quit
            case 0 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            //Filter operation
            case 1 -> { filterOp.filterBy(false, 0); }

            //Transformation operation
            case 2 -> { transformOp.transform(false, 0); }

            //Group By operation
            case 3 -> { groupByOp.groupBy(false, 0); }

            //Print Summary report
            case 4 -> {
                fullReport();
            }
            
            default -> {
                System.out.println("Invalid operation, please try again...");
            }
        }
    }

    private void fullReport(){
        System.out.println("\n=== Headcount by Department ===\n");
        @SuppressWarnings("unchecked")
        Map<String, Long> departmentHeadCount = (Map<String, Long>) groupByOp.groupBy(true, 3);
        departmentHeadCount.forEach((department, count) ->
            System.out.printf("%-20s %5d%n", department, count)
        );

        System.out.println("\n=== Average Salary by Department ===\n");
        @SuppressWarnings("unchecked")
        Map<String, Double> averageSalaryPerDep = (Map<String, Double>) groupByOp.groupBy(true, 4);
        averageSalaryPerDep.forEach((department, avgSalary) ->
             System.out.printf("%-20s %10s%n", department, "$" + String.format("%,.0f", avgSalary))
        );

        System.out.println("\n=== Top Earner per Department ===\n");
        @SuppressWarnings("unchecked")
        Map<String, Optional<Employee>> topEarnersPerDep = (Map<String, Optional<Employee>>) groupByOp.groupBy(true, 6);
        topEarnersPerDep.forEach((department, emp) ->
            System.out.printf("%-20s %-25s %10s%n", department, emp.get().getFirstName() + " " + emp.get().getLastName(), "$" + String.format("%,d", emp.get().getSalary()))
        );
        

        System.out.println("\n=== Employees Hired Before 2020 ===\n");
        @SuppressWarnings("unchecked")
        ArrayList<Employee> hiredBefore2020 = (ArrayList<Employee>) filterOp.filterBy(true, 4, "1999-01-01", "2020-12-31");
        hiredBefore2020.forEach((e) ->
            System.out.printf("%-5d %-15s %-15s %-30s %s%n", e.getId(), e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getHireDate())
        );

        System.out.println("\n=== Total Payroll ===\n");
        int totalPayroll = (int)groupByOp.groupBy(true, 5);
        System.out.printf("%s%n", "$" + String.format("%,d", totalPayroll));

        System.out.println();
    }

    

    public void run(){
        while(true){
            prompt();
            int opNum = scanner.nextInt();
            operation(opNum);
        }
    }
}
