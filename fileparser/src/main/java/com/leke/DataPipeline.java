package com.leke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;
import com.leke.DTO.Status;

public class DataPipeline {
    ArrayList<Employee> employeeList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public DataPipeline(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    private void prompt(){
        System.out.println("""
            [1] Filter
            [2] Transform
            [3] Group By
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
            case 1 -> { filter(); }

            //Transformation operation
            case 2 -> { transform(); }

            //Group By operation
            case 3 -> { groupBy(); }
            
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
                employeeStatusFilter(empAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
                
            }

            case 2 -> {
                System.out.println("Specify the department you would like to filter by?");
                scanner.nextLine();
                String empAns = scanner.nextLine();
                employeeDepartmentFilter(empAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            case 3 -> {
                System.out.println("Specify what age you would like to filter by?");
                scanner.nextLine();
                String empAns = scanner.nextLine();
                employeeAgeFilter(empAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            case 4 -> {
                System.out.println("Specify start date (yyyy-mm-dd) range you would like to filter by?");
                scanner.nextLine();
                String startDateAns = scanner.nextLine();
                System.out.println("Specify end date (yyyy-mm-dd) range you would like to filter by?");
                String endDateAns = scanner.nextLine();
                employeeHireDateFilter(startDateAns, endDateAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            case 5 -> {
                System.out.println("Specify the job title you would like to filter by?");
                scanner.nextLine();
                String empAns = scanner.nextLine();
                employeeJobTitleFilter(empAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            case 6 -> {
                System.out.println("Specify min salary range you would like to filter by?");
                scanner.nextLine();
                int minAns = scanner.nextInt();
                System.out.println("Specify max salary range you would like to filter by?");
                int maxAns = scanner.nextInt();
                employeeSalaryFilter(minAns, maxAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            default -> {
                System.out.println("Invalid operation, please try again...");
            }
        }
    }

    

    private ArrayList<Employee> employeeStatusFilter(String filter){
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

    private ArrayList<Employee> employeeDepartmentFilter(String filter){
        return this.employeeList.stream()
            .filter((e) -> e.getDepartment().toUpperCase().equals(filter.toUpperCase()))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Employee> employeeAgeFilter(String filter){
        return this.employeeList.stream()
            .filter((e) -> e.getAge() == (Integer.parseInt(filter)))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Employee> employeeHireDateFilter(String startDate, String endDate){
        return this.employeeList.stream()
            .filter((e) ->  !e.getHireDate().isBefore(LocalDate.parse(startDate)) && !e.getHireDate().isAfter(LocalDate.parse(endDate)))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Employee> employeeJobTitleFilter(String filter){
        return this.employeeList.stream()
            .filter((e) -> e.getJobTitle().toUpperCase().equals(filter.toUpperCase()))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Employee> employeeSalaryFilter(int min, int max){
        return this.employeeList.stream()
            .filter((e) -> e.getSalary() > min && e.getSalary() < max)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private void transform(){
        System.out.println("""
            What transformation operation would you like to do?

            [1] Extract names
            [2] Extract emails
            [3] Extract salaries
            [0] Go Back
        """);
        int transformOp = scanner.nextInt();
        switch (transformOp) {
            case 0 -> {}
            case 1 -> {
                System.out.println("-------------List of Employee names-------------");
                employeeNameExtractor().forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }
            case 2 -> {
                System.out.println("-------------List of Employee names-------------");
                employeeEmailExtractor().forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
            }

            case 3 -> {
                System.out.println("-------------List of Employee names-------------");
                employeeSalaryExtractor().forEach((e) -> {
                    System.out.println("$" + e + "\n" + "-------------------------------------------------");
                });
            }

        }
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

    private void groupBy(){
         System.out.println("""
            What group by operation would you like to do?

            [1] Group by status
            [2] Group by department
            [0] Go Back
        """);
        int groupByOp = scanner.nextInt();
        switch (groupByOp) {
            case 0 -> {}
            case 1 -> {
                employeestatusGroupBy().forEach((k, v) -> {
                    System.out.println("--------- " + k +" group ---------");
                    v.forEach( e -> {
                       System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                });
            }
            case 2 -> {
                employeeDepartmentGroupBy().forEach((k, v) -> {
                    System.out.println("------------------------ " + k +" group ------------------------3");
                    v.forEach( e -> {
                       System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                });
            }

        }
    }

    private Map<Status, List<Employee>> employeestatusGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getStatus()));
    }

    private Map<String, List<Employee>> employeeDepartmentGroupBy(){
        return this.employeeList.stream().collect(Collectors.groupingBy((e) -> e.getDepartment()));
    }



    public void run(){
        while(true){
            prompt();
            int opNum = scanner.nextInt();
            operation(opNum);
        }
    }
}
