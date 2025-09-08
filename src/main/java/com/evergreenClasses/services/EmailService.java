package com.evergreenClasses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendStudentConfirmationEmail(String toEmail, String studentName, String rollNo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Evergreen Classes - Registration Confirmation");
        message.setText("Dear " + studentName + ",\n\n"
                + "You have been successfully registered in Evergreen Classes.\n"
                + "Your Roll No: " + rollNo + "\n"
                + "Thank you for joining us.\n\n"
                + "Best Regards,\n"
                + "Evergreen Classes Team");

        mailSender.send(message);
    }

}
