package com.evergreenClasses.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evergreenClasses.Dto.ResultForm;
import com.evergreenClasses.model.Result;
import com.evergreenClasses.model.Student;
import com.evergreenClasses.repository.ResultRepository;
import com.evergreenClasses.repository.StudentRepositry;

@Service
public class ResultService {
    ResultRepository resultRepository;
    StudentRepositry studentRepositry;

    public ResultService(ResultRepository resultRepository, StudentRepositry studentRepositry){
        this.resultRepository=resultRepository;
        this.studentRepositry=studentRepositry;
    }

    public void saveResult(ResultForm resultForm){
       Student student =studentRepositry.findById(resultForm.getStudentId())
                       .orElseThrow(()-> new RuntimeException("user not found with "));
        Result result=new Result();
        result.setStudent(student);
        result.setMarksObtained(resultForm.getMarksObtained());
        result.setFullMarks(resultForm.getFullMarks());
        result.setSubjectName(resultForm.getSubjectName());
        result.setTestDate(resultForm.getTestDate());
        resultRepository.save(result);
        
        
    }

    public List<Result> searchByStudentName(String name){
        return resultRepository.findByStudent_NameContainingIgnoreCase(name);
    }

    public List<Result> getAllResult(){
        return resultRepository.findAll();
    }
    public List<Result> searchByStudentRollNo(String rollNo){
        return resultRepository.findByStudent_RollNoContainingIgnoreCase(rollNo);
    }

}
