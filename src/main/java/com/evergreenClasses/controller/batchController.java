package com.evergreenClasses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evergreenClasses.model.Batch;
import com.evergreenClasses.repository.BatchRepository;

@Controller
public class batchController {

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping("/batches")
    public String showBatches(Model model){
        List<Batch> batches=batchRepository.findAll();
        model.addAttribute("batches", batches);
        return "batches";


    }

}
