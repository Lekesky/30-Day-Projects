package com.leke;

import java.io.File;
import java.util.ArrayList;

import com.leke.DTO.Employee;

public class Main {
    public static void main(String[] args) {

        File file = new File("fileparser//src//main//resources//employees.csv");
        FileParser fp = new FileParser(file);
        ArrayList<Employee> employeeList = new ArrayList<>(fp.employeeList);

        // employeeList.forEach((e) -> {
        //     System.out.println(e + "\n" + "-------------------------------------------------");
        // });

        DataPipeline dp = new DataPipeline(employeeList);
        dp.run();
    }
}