package com.tmgreyhat.api.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {


    Optional<Student> findByEmail(String email);

    @Query("select s from Student s where s.email = ?1")
    Student findStudentByEmail(String email);

}
