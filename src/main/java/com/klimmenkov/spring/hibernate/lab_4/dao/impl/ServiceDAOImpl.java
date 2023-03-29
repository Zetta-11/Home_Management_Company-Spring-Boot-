package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.ServiceDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Service;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Service> getAllServices(House house) {
        Session session = manager.unwrap(Session.class);
        List<Service> allServices = session.createQuery("from Service s where s.house = :house", Service.class)
                .setParameter("house", house)
                .getResultList();

        return allServices;
    }

    @Override
    @Transactional
    public void saveService(Service service, House house) {
        Session session = manager.unwrap(Session.class);
        service.setHouse(house);
        session.saveOrUpdate(service);
    }

    @Override
    @Transactional
    public Service getService(int id) {
        Session session = manager.unwrap(Session.class);
        Service service = session.get(Service.class, id);

        return service;
    }

    @Override
    @Transactional
    public void deleteService(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Service where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
