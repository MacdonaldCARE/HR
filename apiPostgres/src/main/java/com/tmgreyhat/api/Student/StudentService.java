package com.tmgreyhat.api.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final  StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsList(){
       return  studentRepository.findAll();

    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw  new IllegalStateException("WELL EMAIL AlREADY TAKEN");
        }
        return  studentRepository.save(student);
    }

    public void removeStudent(Long id) {

        boolean existsById = studentRepository.existsById(id);

        if(!existsById){
            throw  new IllegalStateException("Student does not exist "+id);

        }

        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {

        boolean existsById = studentRepository.existsById(student.getId());
        if (!existsById){
            throw  new IllegalStateException("Student ID does not exist "+student.getId());
        }

        return  studentRepository.save(student);
    }
}
