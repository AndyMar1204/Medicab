package com.andy.Medicab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail() throws MailException {

    }
}
