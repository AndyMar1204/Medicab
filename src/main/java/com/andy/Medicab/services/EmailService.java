package com.andy.Medicab.services;

public interface EmailService {
    void sendMail(String to, String subject, String text);
}
