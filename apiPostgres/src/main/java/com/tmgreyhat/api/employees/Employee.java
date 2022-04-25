package com.tmgreyhat.api.employees;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tmgreyhat.api.departments.Department;
import com.tmgreyhat.api.jobTitles.JobTitle;
import org.w3c.dom.stylesheets.LinkStyle;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            allocationSize = 1,
            sequenceName = "employee_sequence"
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "employee_sequence")
    @Column(name = "employee_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isSupervisor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jobTitle_id")
    @JsonBackReference
    private JobTitle jobTitle;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;


    private String gender;

/*    @JoinTable(name = "EMPLOYEE_SUBORDINATES", joinColumns = {
            @JoinColumn(name = "ADDING_USER", referencedColumnName = "EMPLOYEE_ID", nullable =   false)}, inverseJoinColumns = {
            @JoinColumn(name = "ADD_EMPLOYEE", referencedColumnName = "EMPLOYEE_ID", nullable = false)})

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Employee> subordinates;

    @ManyToMany(mappedBy = "subordinates")
    private Collection<Employee> addEmployee;*/

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
                    JobTitle title,
                    Department department,

                    String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = title;
        this.department = department;

        this.gender = gender;
    }public Employee(
                    String firstName,
                    String lastName,
                    String email,
                    JobTitle title,
                    Department department,
                    Employee supervisor,
                    String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = title;
        this.department = department;
        this.supervisor = supervisor;
        this.gender = gender;
    }

    public Employee(
            Long id,
            String firstName,
            String lastName,
            String email,
            Department department,
            JobTitle title,
            String gender) {
        this(firstName, lastName, email, title, department,gender);
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
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

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isSupervisor=" + isSupervisor +
                ", jobTitle=" + jobTitle +
                ", department=" + department +
                ", gender='" + gender + '\'' +
                ", supervisor=" + supervisor +
                ", subordinates=" + subordinates +
                '}';
    }
}
