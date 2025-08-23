package com.evergreenClasses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evergreenClasses.model.Result;

public interface ResultRepository extends JpaRepository<Result,Long>{
    // List<Result> findBySubjectName(String subjectName);
    // List<Result> findByStudent_StudentClass(String studentClass);
    List<Result> findBySubjectName(String subjectName);
    List<Result> findByStudent_StudentClass(String studentClass);

}
