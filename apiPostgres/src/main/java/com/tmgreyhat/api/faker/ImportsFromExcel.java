package com.tmgreyhat.api.faker;


import com.tmgreyhat.api.employees.Employee;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class ImportsFromExcel {

    static  class CareEmployee{

        String employeeNumber;
        String fullname;
        String firstName;
        String lastName;
        String jobTitle;
        CareEmployee supervisor;
        String supervisorName;
        String department;
        String phoneNumber;
        String email;
        String systemRole;





        public CareEmployee(String employeeNumber,
                            String fullname,

                            String jobTitle,
                            String department,
                            String phoneNumber,
                            String email) {
            this.employeeNumber = employeeNumber;
         this.fullname = fullname;
            this.jobTitle = jobTitle;
            this.department = department;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        @Override
        public String toString() {
            return "CareEmployee{" +
                    "employeeNumber='" + employeeNumber + '\'' +
                    ", fullName='" + fullname + '\'' +
                    ", jobTitle='" + jobTitle + '\'' +
                    ", supervisor='" + supervisor.employeeNumber + '\'' +
                    ", department='" + department + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public  CareEmployee() {

        }
    }
    private static final String COMMA_DELIMITER = ",";
   static List<CareEmployee> careEmployeeList = new ArrayList<>();
   static List<CareEmployee> careEmployeeListWithSupervisors = new ArrayList<>();
   static List<CareEmployee> contactList = new ArrayList<>();
    public static void main(String[] args) {

        for (List<String> record : readReportingLines()){


            CareEmployee employee = new CareEmployee();

            employee.employeeNumber = record.get(0);
            employee.fullname = record.get(1);
            employee.jobTitle = record.get(2);
            employee.supervisorName = record.get(3);
            employee.department = record.get(4);
            employee.systemRole = "ROLE_GEN";
            employee.phoneNumber = "07751XXXXX";
            employee.email = "employee@care.co.zw";
            careEmployeeList.add(employee);
           // System.out.println(record);
        }

       // careEmployeeList.forEach(System.out::println);

        for (CareEmployee employee: careEmployeeList){

            String supervisor = employee.supervisorName;
         //   String supervisorEmployeeNumber = careEmployeeList.stream().filter(e-> e.fullname.equalsIgnoreCase( supervisor)).findFirst().get().employeeNumber;

            Optional<CareEmployee> careSupervisor = careEmployeeList.stream().filter(e -> e.fullname.equalsIgnoreCase(supervisor)).findFirst();

            if(careSupervisor.isPresent()){
                employee.supervisor = careSupervisor.get();

            }else {

                CareEmployee supervisorDefault = new CareEmployee();
                supervisorDefault.employeeNumber = "101010";

                employee.supervisor = supervisorDefault ;
            }


            careEmployeeListWithSupervisors.add(employee);



        }

        careEmployeeListWithSupervisors.forEach(careEmployee -> {

            System.out.println(" employee "+ careEmployee);
        });

     //   careEmployeeListWithSupervisors.forEach(System.out::println);

    }

    static List<CareEmployee> readContactList (){

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tmbizvo\\Downloads\\Documents\\care_contact_list.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



      for (List<String> record : records){


          CareEmployee employee = new CareEmployee();

          employee.employeeNumber = record.get(0).replaceFirst("^0+(?!$)", "");
          employee.phoneNumber = record.get(6);
          employee.email = record.get(5);
          contactList.add(employee);

      }

        return contactList;
    }
    static List<List<String>> readReportingLines (){

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tmbizvo\\Downloads\\Documents\\care_repo_csv.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(records);

        //records.forEach(record-> System.out.println(record.get(0)));

        return  records;
    }




}
