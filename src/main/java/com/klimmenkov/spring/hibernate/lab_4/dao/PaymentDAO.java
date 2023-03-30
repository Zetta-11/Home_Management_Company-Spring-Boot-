package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;

import java.util.List;

public interface PaymentDAO {

    List<Payment> getAllPayments(House house);

    PaymentDetails getPaymentDetails(Payment payment);

    void savePayment(Payment payment, House house);

    Payment getPayment(int id);

    void deletePayment(int id);
}
