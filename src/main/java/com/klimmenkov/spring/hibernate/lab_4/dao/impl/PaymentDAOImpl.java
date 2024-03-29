package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.PaymentDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PaymentDAOImpl implements PaymentDAO {
    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Payment> getAllPayments(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Payment p where p.house = :house", Payment.class)
                .setParameter("house", house).getResultList();
    }

    @Override
    @Transactional
    public List<Payment> getAllIncomePayments(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Payment p where p.house = :house and p.paymentType = :type", Payment.class)
                .setParameter("house", house)
                .setParameter("type", "income")
                .getResultList();
    }

    @Override
    @Transactional
    public List<Payment> getAllExpensesPayments(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Payment p where p.house = :house and p.paymentType = :type", Payment.class)
                .setParameter("house", house)
                .setParameter("type", "expenses")
                .getResultList();
    }

    @Override
    @Transactional
    public List<Payment> getFilteredPaymentsByTenant(House house, String userLogin) {
        Session session = manager.unwrap(Session.class);
        Query<Payment> q = session.createQuery("select p from Payment p " +
                        "inner join p.tenant t " +
                        "where t.user.login = :login",
                Payment.class);
        q.setParameter("login", userLogin);

        List<Payment> filteredPayments = q.getResultList();

        return filteredPayments;
    }

    @Override
    @Transactional
    public List<Payment> getFilteredPaymentsByType(House house, String type) {
        Session session = manager.unwrap(Session.class);
        Query<Payment> q = session.createQuery("from Payment " + "where paymentType = :type", Payment.class);
        q.setParameter("type", type);

        List<Payment> filteredPayments = q.getResultList();

        return filteredPayments;
    }

    @Override
    @Transactional
    public PaymentDetails getPaymentDetails(Payment payment) {
        Session session = manager.unwrap(Session.class);
        PaymentDetails details = (PaymentDetails) session.createQuery("from PaymentDetails d where d.id = :paymentID")
                .setParameter("paymentID", payment.getId())
                .getSingleResult();

        return details;
    }

    @Override
    @Transactional
    public Long getSumOfIncomePayments() {
        Session session = manager.unwrap(Session.class);
        Long sum = (long) session.createQuery("select sum(p.sum) from Payment p where p.paymentType = :type")
                .setParameter("type", "income")
                .getSingleResult();

        return sum;
    }

    @Override
    @Transactional
    public Long getSumOfExpensesPayments() {
        Session session = manager.unwrap(Session.class);
        Long sum = (long) session.createQuery("select sum(p.sum) from Payment p where p.paymentType = :type")
                .setParameter("type", "expenses")
                .getSingleResult();

        return sum;
    }

    @Override
    @Transactional
    public void savePayment(Payment payment, House house) {
        Session session = manager.unwrap(Session.class);
        payment.setHouse(house);
        session.saveOrUpdate(payment);
    }

    @Override
    @Transactional
    public Payment getPayment(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(Payment.class, id);
    }

    @Override
    @Transactional
    public void deletePayment(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Payment where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
