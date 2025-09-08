package com.evergreenClasses.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evergreenClasses.model.Batch;
import com.evergreenClasses.model.Student;
import com.evergreenClasses.repository.BatchRepository;
import com.evergreenClasses.repository.StudentRepositry;
import com.evergreenClasses.services.EmailService;
import com.evergreenClasses.services.StudentService;

@Controller
@RequestMapping("/admin")
public class adminStudentController {

    @Autowired
    StudentRepositry studentRepositry;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    private EmailService emailService;

     @GetMapping("/registrations")
    public String showRegisterForm(Model model){
        model.addAttribute("student", new Student());
        List<Batch> batches=batchRepository.findAll();
        model.addAttribute("batches",batches);
        return "registration_form";
    }

    @PostMapping("/registrations")
    public String processRegistration(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes){
        
         
        //setiing batch_id as forieng key to the student
        Long  bId=student.getBatch().getBatchId();
        Batch batch=batchRepository.findByBatchId(bId);
        //System.out.println("batch *********: "+batch);
        student.setBatch(batch);


        //setting batch name
        String batchName=batch.getBatchName();
        student.setBatchName(batchName);


        Student saveStudent = studentRepositry.save(student);
       
        saveStudent.setRollNo(saveStudent.getBatchYear()+saveStudent.getStudentClass()+saveStudent.getId());
        studentRepositry.save(saveStudent);
        String generatedRollNo =saveStudent.getRollNo();
        emailService.sendStudentConfirmationEmail(saveStudent.getEmail(),saveStudent.getName(),generatedRollNo);

       // System.out.println("roll No : "+generatedRollNo);

        redirectAttributes.addFlashAttribute("rollNo",generatedRollNo);

        return "redirect:/admin/registrations?success";
    }


    // Student Student profile

    @GetMapping("/students")
    public String showStuden(Model model){
        List<Student> students=studentRepositry.findAll();
        model.addAttribute("students", students);
        // model.addAttribute("keyword", keyword);
        // model.addAttribute("successMessage",successMessage);
        return "student";
    }

    //get details of student
    @GetMapping("/students/view/{id}")
    public String ShowStudentProfile(@PathVariable ("id") Long Id, Model model ){

        model.addAttribute("student",studentRepositry.findById(Id).orElseThrow());

        return "view";
    }

    //Update student
    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute Student student){
        Student updateStudent=studentRepositry.save(student);
        updateStudent.setRollNo(updateStudent.getBatchYear()+updateStudent.getStudentClass()+updateStudent.getId());
        studentRepositry.save(updateStudent);
        
        return "redirect:/admin/students";
    }

    //delete student

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable ("id") Long Id){
        studentRepositry.deleteById(Id);

        return "redirect:/admin/students";

    }

    @GetMapping("/students/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Student> result = studentRepositry.findByNameContainingIgnoreCase(keyword);
        if (result.isEmpty()) {
            //result=studentRepositry.findByRollNoContainingIgnoreCase(keyword).orElse(null);
            model.addAttribute("students",studentService.searchStudentByRollNo(keyword));
            return "student";
        }
        model.addAttribute("students", result);
        return "student";
    }
}
