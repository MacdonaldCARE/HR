package com.tmgreyhat.api.departments;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.tmgreyhat.api.employees.Employee;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @SequenceGenerator(
            name = "department_sequence",
            allocationSize = 1,
            sequenceName = "department_sequence"
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "department_sequence")
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;

    public Department() {
    }

    public Department(
            String name,
            String description) {
        this.name = name;
        this.description = description;
    }

    public Department(
            Long id,
            String name,
            String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonManagedReference(value = "department-employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
