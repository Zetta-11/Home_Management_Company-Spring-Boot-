package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.PaymentDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;
import com.klimmenkov.spring.hibernate.lab_4.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDAO paymentDAO;

    @Override
    public List<Payment> getAllPayments(House house) {
        return paymentDAO.getAllPayments(house);
    }

    @Override
    public PaymentDetails getPaymentDetails(Payment payment) {
        return paymentDAO.getPaymentDetails(payment);
    }

    @Override
    public Long getSumOfIncomePayments() {
        return paymentDAO.getSumOfIncomePayments();
    }

    @Override
    public void savePayment(Payment payment, House house) {
        paymentDAO.savePayment(payment, house);
    }

    @Override
    public Payment getPayment(int id) {
        return paymentDAO.getPayment(id);
    }

    @Override
    public void deletePayment(int id) {
        paymentDAO.deletePayment(id);
    }
}
