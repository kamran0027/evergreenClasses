package com.evergreenClasses.controller.admin;

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

import com.evergreenClasses.Dto.ResultForm;
import com.evergreenClasses.repository.ResultRepository;
import com.evergreenClasses.repository.StudentRepositry;
import com.evergreenClasses.services.ResultService;

@Controller
@RequestMapping("/admin")
public class adminResultController {

    private final ResultRepository resultRepository;

    @Autowired
    ResultService resultService;
    @Autowired
    StudentRepositry studentRepositry;

    adminResultController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

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

    @GetMapping("/result/show")
    public String showResultList(Model model){
        //model.addAttribute("results",resultService.searchByStudentName())
        model.addAttribute("results", resultService.getAllResult());
        return "view_result";
    }

    // @GetMapping("/result/show/edit/{id}")
    // public String editResult(@PathVariable("id") Long id, Model model) {
    //     model.addAttribute("result", resultRepository.findById(id).orElseThrow(null));
    //     model.addAttribute("students", studentRepositry.findAll());
    //     return "result_form";
    // }

    @GetMapping("/result/show/delete/{id}")
    public String deleteResult(@PathVariable("id") Long id) {
        resultRepository.deleteById(id);
        return "redirect:/admin/result/show";
    }

    @GetMapping("/result/show/search")
    public String searchResult(@RequestParam("keyword") String name, Model model){
        model.addAttribute("results",resultService.searchByStudentName(name));
        return "view_result";
    }


}
