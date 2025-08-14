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

import com.evergreenClasses.model.Batch;
import com.evergreenClasses.model.Student;
import com.evergreenClasses.repository.BatchRepository;
import com.evergreenClasses.repository.StudentRepositry;

@Controller
@RequestMapping("/admin/batches")
public class adminBatchController {

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    StudentRepositry studentRepositry;

    // show all batch
    @GetMapping
    public String listBatches(Model model){
        model.addAttribute("batches", batchRepository.findAll());
        return "admin_batches";
    }

    //show add form
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("batch", new Batch());
        return "batch_form";
    }

    //save batch
    @PostMapping("/save")
    public String saveBatch(@ModelAttribute Batch batch){
        batchRepository.save(batch);
        return "redirect:/admin/batches";

    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String editBatch(@PathVariable("id") Long id, Model model) {
        model.addAttribute("batch", batchRepository.findById(id).orElseThrow(null));
        return "batch_form";
    }

    // Delete batch
    @GetMapping("/delete/{id}")
    public String deleteBatch(@PathVariable("id") Long id) {
        batchRepository.deleteById(id);
        return "redirect:/admin/batches";
    }

    // Search
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Batch> result = batchRepository.findByBatchNameContainingIgnoreCase(keyword);
        model.addAttribute("batches", result);
        return "admin_batches";
    }

    // view batch
    @GetMapping("/details/{id}")
    public String batchDetails(@PathVariable("id") Long Id, Model model){
        Batch batch=batchRepository.findByBatchId(Id);
        List<Student> students=studentRepositry.findByBatch_BatchId(Id);
        model.addAttribute("students", students);
        model.addAttribute("batch", batch);
        model.addAttribute("count", students.size());

        return "batch_details";
    }

}
