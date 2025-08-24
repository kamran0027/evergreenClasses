package com.evergreenClasses.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evergreenClasses.model.Student;


public interface StudentRepositry extends JpaRepository<Student,Long> {

    // this is important to understand
    List<Student> findByBatch_BatchId(Long batchId);

    List<Student> findByNameContainingIgnoreCase(String Name);
    List<Student> findByRollNoContainingIgnoreCase(String RollNo);
    //Optional<Student> findByRollNo(String RollNo);
    

}
