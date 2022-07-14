package com.tmgreyhat.api.employees;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tmgreyhat.api.departments.Department;
import com.tmgreyhat.api.jobTitles.JobTitle;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.stylesheets.LinkStyle;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Employee {



    @Id
    private String employeeNumber;

    @Transient
    private String supervisorNumber;
    @Column
    private  String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private Boolean isSupervisor;
    @Column(nullable = true)
    private String jobGrade;

    private int jobGradeStep;
    private String position;
    private String department;

    @Transient
    private String systemRole;


    @ManyToOne(fetch = FetchType.LAZY)
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private Set<Employee> subordinates;

    public Employee() {
    }

    public Employee(

                    String fullName,
                    String email,
                    String department
                    ) {
        this.fullName = fullName;

        this.email = email;
        this.department = department;

    }
    public Employee(
                    String fullName,
                    String email,
                    String phoneNumber,
                    String jobGrade,
                    String position,
                    String systemRole,
                    String department,
                    Employee supervisor,
                    boolean isSupervisor,
                    String employeeNumber) {

        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobGrade = jobGrade;
        this.position = position;
        this.systemRole = systemRole;
        this.isSupervisor = isSupervisor;
        this.department = department;
        this.supervisor = supervisor;

        this.employeeNumber = employeeNumber;
    }


    public  Employee(String employeeNumber){

        this.employeeNumber = employeeNumber;
    }
    public Employee(
            String fullName,
            String email,
            String phoneNumber,
            String jobGrade,
            String position,
            String systemRole,
            String department,
            boolean isSupervisor,

            String employeeNumber) {
        this.fullName = fullName;

        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobGrade = jobGrade;
        this.position = position;
        this.systemRole = systemRole;
        this.isSupervisor = isSupervisor;
        this.department = department;

        this.employeeNumber = employeeNumber;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    @JsonManagedReference(value = "subordinates")
    public Collection<Employee> getSubordinates() {
        return subordinates;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonBackReference
    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public void setSupervisor(Boolean supervisor) {
        isSupervisor = supervisor;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public  void setIsSupervisor(Boolean isSupervisor){

        this.isSupervisor = isSupervisor;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public  Boolean getIsSupervisor(){

        return  this.isSupervisor;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSupervisorNumber() {
        return supervisorNumber;
    }

    public void setSupervisorNumber(String supervisorNumber) {
        this.supervisorNumber = supervisorNumber;
    }

    public int getJobGradeStep() {
        return jobGradeStep;
    }

    public void setJobGradeStep(int jobGradeStep) {
        this.jobGradeStep = jobGradeStep;
    }
}
