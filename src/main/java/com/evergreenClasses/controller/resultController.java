package com.evergreenClasses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evergreenClasses.model.Student;
import com.evergreenClasses.services.ResultService;
import com.evergreenClasses.services.StudentService;

@Controller
@RequestMapping("/public")
public class resultController {

    private final ResultService resultService;

    private final StudentService studentService;

    resultController(StudentService studentService,ResultService resultService){
        this.studentService=studentService;
       
        this.resultService = resultService;
    }


    @GetMapping("/result")
    public String showResult(){
        return "Student_Result_Check_form";
    }
    @GetMapping("/result/view")
    public String viewResult(@RequestParam("rollNo") String rollNo, Model model){
        System.out.println("***********************************************************************");
        Student student=studentService.getStudentByRollNo(rollNo);
        if(student==null){
            String errorMessage="You have enter wrong rollNo";
            model.addAttribute("error",errorMessage);
            return "Student_Result_Check_form";
        }
        model.addAttribute("students", studentService.getStudentByRollNo(rollNo));
        model.addAttribute("results",resultService.searchByStudentRollNo(rollNo));
        return "public_view_result";
    }

}
