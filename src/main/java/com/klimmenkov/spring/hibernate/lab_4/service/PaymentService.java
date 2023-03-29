package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments(House house);

    void savePayment(Payment payment, House house);

    Payment getPayment(int id);

    void deletePayment(int id);
}
