package com.klimmenkov.spring.hibernate.lab_4.service;

import com.itextpdf.text.DocumentException;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailSenderService {
    void sendEmail(String to, String userLogin) throws DocumentException, MessagingException;

    void sendEmailWithAttachment(String to);

}
