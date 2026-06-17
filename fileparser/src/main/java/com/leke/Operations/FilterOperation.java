package com.leke.Operations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.leke.DTO.Employee;

public class FilterOperation {
    ArrayList<Employee> employeeList;
    private static final Scanner scanner = new Scanner(System.in);
    
    public FilterOperation(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    public Object filterBy(boolean skipPrompt, int requestedOp, String... filterRequest){
        int filterOp = 0;
        if(!skipPrompt){
            System.out.println("""
                What filter operation would you like to do?

                [1] Filter by employee status
                [2] Filter by department
                [3] Filter by age
                [4] Filter by hire date
                [5] Filter by job title
                [6] Filter by salary
                [7] Filter by Highest-paid employee
                [0] Go Back
            """);
            
            filterOp = scanner.nextInt();
        }else{
            filterOp = requestedOp;
        }


        return switch (filterOp) {
            //Go back
            case 0 -> { yield null; }

            //Filter by Status
            case 1 -> {
                if(!skipPrompt){
                    System.out.println("Would you like ACTIVE or INACTIVE employees?");
                    scanner.nextLine();
                    String empAns = scanner.nextLine();
                    employeeStatusFilter(empAns).forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeStatusFilter(filterRequest[0]);
                }
                
            }

            //Filter by Department
            case 2 -> {
                if(!skipPrompt){
                    System.out.println("Specify the department you would like to filter by?");
                    scanner.nextLine();
                    String empAns = scanner.nextLine();
                    employeeDepartmentFilter(empAns).forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeDepartmentFilter(filterRequest[0]);
                }
                
            }

            //Filter by Age
            case 3 -> {
                if(!skipPrompt){
                    System.out.println("Specify what age you would like to filter by?");
                    scanner.nextLine();
                    String empAns = scanner.nextLine();
                    employeeAgeFilter(empAns).forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeAgeFilter(filterRequest[0]);
                }
                
            }

            //Filter by Hire Date range
            case 4 -> {
                if(!skipPrompt){
                    System.out.println("Specify start date (yyyy-mm-dd) range you would like to filter by?");
                scanner.nextLine();
                String startDateAns = scanner.nextLine();
                System.out.println("Specify end date (yyyy-mm-dd) range you would like to filter by?");
                String endDateAns = scanner.nextLine();
                employeeHireDateFilter(startDateAns, endDateAns).forEach((e) -> {
                    System.out.println(e + "\n" + "-------------------------------------------------");
                });
                    yield null;
                }else{
                    yield employeeHireDateFilter(filterRequest[0], filterRequest[1]);
                }
            }

            //Filter by Job Title
            case 5 -> {
                if(!skipPrompt){
                    System.out.println("Specify the job title you would like to filter by?");
                    scanner.nextLine();
                    String empAns = scanner.nextLine();
                    employeeJobTitleFilter(empAns).forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeJobTitleFilter(filterRequest[0]);
                }
                
            }

            //Filter by Salary range
            case 6 -> {
                if(!skipPrompt){
                    System.out.println("Specify min salary range you would like to filter by?");
                    scanner.nextLine();
                    int minAns = scanner.nextInt();
                    System.out.println("Specify max salary range you would like to filter by?");
                    int maxAns = scanner.nextInt();
                    employeeSalaryFilter(minAns, maxAns).forEach((e) -> {
                        System.out.println(e + "\n" + "-------------------------------------------------");
                    });
                    yield null;
                }else{
                    yield employeeSalaryFilter(Integer.parseInt(filterRequest[0]), Integer.parseInt(filterRequest[1]));
                }
            }

            //Filter by highest paid Salary
            case 7 -> {
                if(!skipPrompt){
                    System.out.println("------------------------------------------------");
                    System.out.println(employeeHighestPaidFilter());
                    yield null;
                }else{
                    yield employeeHighestPaidFilter();
                }
                 
            }

            default -> {
                System.out.println("Invalid operation, please try again...");
                yield null;
            }
        };
    }

    

    private ArrayList<Employee> employeeStatusFilter(String filter){
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        switch(filter.toUpperCase()){
            case "INACTIVE" -> {
                filteredEmployees = this.employeeList.stream()
                    .filter((e) -> e.getStatus().toString().equals("INACTIVE"))
                    .collect(Collectors.toCollection(ArrayList::new));
                
            }
            case "ACTIVE" -> {
                filteredEmployees = this.employeeList.stream()
                    .filter((e) -> e.getStatus().toString().equals("ACTIVE"))
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

    private Employee employeeHighestPaidFilter(){
        return this.employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();
    }
}
