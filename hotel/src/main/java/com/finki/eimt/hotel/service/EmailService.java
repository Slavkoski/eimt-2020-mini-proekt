package com.finki.eimt.hotel.service;

public interface EmailService {
    void sendMail(String to, String subject, String text);
}
