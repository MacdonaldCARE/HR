package com.tmgreyhat.api.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {


    @Autowired
    private  StudentRepository underTest;
    @Test
    void itShouldCheckIfStudentExistsByEmail() {


        String email = "testStudent@gmail.com";
        //given
        Student student = new Student(

                "testStudent",
                email,
                LocalDate.of(1994, Month.SEPTEMBER, 27));
        //when

        underTest.save(student);
        boolean exists = underTest.findByEmail(email).isPresent();



        //then


        assertThat(exists).isTrue();

    }
}