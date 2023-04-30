package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.service.EmailSenderService;
import com.klimmenkov.spring.hibernate.lab_4.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PaymentService paymentService;

    @Override
    public void sendEmail(String to) {
        Long availableSum = paymentService.getSumOfAvailableMoney();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("klim.menkov@gmail.com");
        message.setTo(to);
        message.setSubject("Квитанція з доступною сумою");
        message.setText("Добрий день! \nДоступна сума для використання в ОСББ: "
                + availableSum + "\nЗ повагою, команда HMC\n\nБУДЬ ЛАСКА, НЕ ВІДПОВІДАЙТЕ НА ЦЕ ПОВІДОМЛЕННЯ!");
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String to) {

    }
}
