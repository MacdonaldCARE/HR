package com.tmgreyhat.api.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e from Employee e where e.supervisor.id = ?1")
    List<Employee> findEmployeeSupervisedBy(String id);

    Optional<Employee> findByEmail(String email);

   // List<Employee> findByDepartmentId(Long id);

    @Query(value = "SELECT * FROM employees  where is_supervisor = true", nativeQuery = true)
    List<Employee>  getAllSupervisors();

}
