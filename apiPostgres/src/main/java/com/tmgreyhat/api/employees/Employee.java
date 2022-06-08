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
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Transient
    private  String fullName;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private Boolean isSupervisor;

    private String jobGrade;

    private String position;

    private String department;

    @Transient
    private String systemRole;
    private String gender;

    private String idNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private Set<Employee> subordinates;

    public Employee() {
    }

    public Employee(
                    String firstName,
                    String lastName,
                    String email,
                    String department,
                    String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.gender = gender;
    }
    public Employee(
                    String firstName,
                    String lastName,
                    String email,
                    String phoneNumber,
                    String jobGrade,
                    String position,
                    String systemRole,
                    String department,
                    Employee supervisor,
                    boolean isSupervisor,
                    String gender,
                    String employeeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobGrade = jobGrade;
        this.position = position;
        this.systemRole = systemRole;
        this.isSupervisor = isSupervisor;
        this.department = department;
        this.supervisor = supervisor;
        this.gender = gender;
        this.employeeNumber = employeeNumber;
    }

    public Employee(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String jobGrade,
            String position,
            String systemRole,
            String department,
            boolean isSupervisor,
            String gender,
            String employeeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobGrade = jobGrade;
        this.position = position;
        this.systemRole = systemRole;
        this.isSupervisor = isSupervisor;
        this.department = department;

        this.gender = gender;
        this.employeeNumber = employeeNumber;
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        return this.lastName+" "+ this.firstName;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isSupervisor=" + isSupervisor +
                ", jobGrade='" + jobGrade + '\'' +
                ", department='" + department + '\'' +
                ", systemRole='" + systemRole + '\'' +
                ", subordinates=" + subordinates +
                '}';
    }
}
