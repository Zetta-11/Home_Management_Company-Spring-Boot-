package com.klimmenkov.spring.hibernate.lab_4.service;

import com.itextpdf.text.DocumentException;

import javax.mail.MessagingException;

public interface EmailSenderService {
    void sendEmailForPayments(String to, String userLogin);

    void sendEmailForPaymentsWithAttachment(String to, String userLogin) throws DocumentException, MessagingException;

    void sendEmailForSearchWithAttachment(String to, String keyword) throws DocumentException, MessagingException;

}
