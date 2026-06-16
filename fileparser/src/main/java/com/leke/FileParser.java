package com.leke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.leke.DTO.Employee;
import com.leke.DTO.Status;

public class FileParser {
    ArrayList<Employee> employeeList = new ArrayList<>();

    File file;
    static final String COMMA_DELIMITER = ",";
    
    public FileParser(File file){
        this.file = file;
        importFile(this.file);
    }

    private void importFile(File file){
        System.out.println("Importing from " + file.getName() + "...");
        
        try(Scanner reader = new Scanner(file)){
            while(reader.hasNextLine()){
                Employee e = new Employee();
                
                String line = reader.nextLine();
                String[] values = line.split(COMMA_DELIMITER);

                //Skip header
                if(values[0].equals("id")) continue; 

                //Set Employee values per line
                e.setId(Integer.parseInt(values[0]));
                e.setFirstName(values[1]);
                e.setLastName(values[2]);
                e.setEmail(values[3]);
                e.setDepartment(values[4]);
                e.setJobTitle(values[5]);
                e.setSalary(Integer.parseInt(values[6]));
                e.setHireDate(LocalDate.parse(values[7]));
                e.setStatus(values[8].equals("ACTIVE") ? Status.ACTIVE : Status.INACTIVE);
                e.setAge(Integer.parseInt(values[9]));

                this.employeeList.add(e);

            }
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Import finished!");
    }

    public ArrayList<Employee> getEmployeeList(){
        return this.employeeList;
    }

}
