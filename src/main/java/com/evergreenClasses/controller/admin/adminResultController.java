package com.evergreenClasses.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evergreenClasses.Dto.ResultForm;
import com.evergreenClasses.repository.StudentRepositry;
import com.evergreenClasses.services.ResultService;

@Controller
@RequestMapping("/admin")
public class adminResultController {

    @Autowired
    ResultService resultService;
    @Autowired
    StudentRepositry studentRepositry;

    @GetMapping("/result")
    public String resultSection(){
        return "result";
    }

    @GetMapping("/result/upload")
    public String showResultForm(Model model){
        model.addAttribute("result", new ResultForm());
        model.addAttribute("students", studentRepositry.findAll());
        return "result_form";
    }

    @PostMapping("/result/upload")
    public String uploadResult(@ModelAttribute ResultForm resultForm, RedirectAttributes redirectAttributes ){
        resultService.saveResult(resultForm);
        return "redirect:/admin/result";
    }

    // @PostMapping("/result/save"){
    //     public String uploadResult(@PathVariable )
    // }

}
