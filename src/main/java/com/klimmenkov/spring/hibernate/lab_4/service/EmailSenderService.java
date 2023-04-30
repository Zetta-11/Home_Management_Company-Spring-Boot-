package com.klimmenkov.spring.hibernate.lab_4.service;

public interface EmailSenderService {

    void sendEmail (String to);

    void sendEmailWithAttachment(String to);
}
