package com.andy.Medicab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("medicab2022@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try{
            emailSender.send(message);
            System.out.println("Message envoyé avec success");
        }catch(Exception ex){
            ex.printStackTrace();
        }


    }
}
