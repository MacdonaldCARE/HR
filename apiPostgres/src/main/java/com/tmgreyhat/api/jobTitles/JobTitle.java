package com.tmgreyhat.api.jobTitles;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tmgreyhat.api.employees.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "job_titles")
public class JobTitle {

    @Id
    @SequenceGenerator(
            name = "job_title_sequence",
            allocationSize = 1,
            sequenceName = "job_title_sequence"
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "job_title_sequence")

    private Long id;
    private String jobTitle;
    private String jobGrade;
    private String description;
    @OneToMany(mappedBy = "jobTitle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;


    public JobTitle() {
    }

    public JobTitle(String jobTitle, String jobGrade, String description) {
        this.jobTitle = jobTitle;
        this.jobGrade = jobGrade;
        this.description = description;
    }

    public JobTitle(String jobTitle, String jobGrade) {
        this.jobTitle = jobTitle;
        this.jobGrade = jobGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonManagedReference(value = "jobTitle-employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "JobTitle{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobGrade='" + jobGrade + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
