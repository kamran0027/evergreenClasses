package com.evergreenClasses.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class adminController {
    @GetMapping
    public String showAdminDashboard(){
        return "admin_home";
    }
    
   

   








}
