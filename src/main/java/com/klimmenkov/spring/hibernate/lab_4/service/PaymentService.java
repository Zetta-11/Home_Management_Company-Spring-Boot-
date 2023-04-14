package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments(House house);

    List<Payment> getAllIncomePayments(House house);

    List<Payment> getAllExpensesPayments(House house);

    List<Payment> getFilteredPaymentsByTenant(House house, String userLogin);

    List<Payment> getFilteredPaymentsByType(House house, String type);

    PaymentDetails getPaymentDetails(Payment payment);

    Long getSumOfAvailableMoney();

    void savePayment(Payment payment, House house);

    Payment getPayment(int id);

    void deletePayment(int id);
}
