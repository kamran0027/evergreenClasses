package com.evergreenClasses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class contactController{

    
    @GetMapping("/contact")
    public String ShownContactForm(){
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam String name, @RequestParam String phone,  
                                    @RequestParam String message){
            System.out.println("Name : "+name);
            System.out.println("Phone no. : "+phone);
            System.out.println("Message : "+message);

            return "redirect:/contact?success";
                            
        }

}

