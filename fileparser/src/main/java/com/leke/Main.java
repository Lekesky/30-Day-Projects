package com.leke;

import java.io.File;
import java.util.ArrayList;

import com.leke.DTO.Employee;

public class Main {
    public static void main(String[] args) {

        File file = new File("C://Users//lekeo//Desktop//30-Day-Projects//fileparser//src//main//java//com//leke//resouces//employees.csv");
        FileParser fp = new FileParser(file);
        ArrayList<Employee> employeeList = new ArrayList<>(fp.employeeList);

        employeeList.forEach((e) -> {
            System.out.println(e + "\n" + "-------------------------------------------------");
        });
    }
}