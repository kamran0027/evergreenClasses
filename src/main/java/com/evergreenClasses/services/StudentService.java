package com.evergreenClasses.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evergreenClasses.model.Student;
import com.evergreenClasses.repository.StudentRepositry;

@Service
public class StudentService {

    private final StudentRepositry studentRepositry;

    public StudentService(StudentRepositry studentRepositry){
        this.studentRepositry=studentRepositry;
    }

    public List<Student> getStudentByRollNo(String rollNo){
        return studentRepositry.findByRollNoContainingIgnoreCase(rollNo);

    }
}
