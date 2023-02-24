package com.klimmenkov.spring.hibernate.lab_4.dao.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.HouseDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HouseDAOImpl implements HouseDAO {


    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<House> getAllHouses() {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from House ", House.class).getResultList();
    }

    @Override
    @Transactional
    public void saveHouse(House house) {
        Session session = manager.unwrap(Session.class);

        session.saveOrUpdate(house);
    }

    @Override
    @Transactional
    public House getHouse(int id) {
        Session session = manager.unwrap(Session.class);
        return session.get(House.class, id);
    }

    @Override
    @Transactional
    public void deleteHouse(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from House where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();

    }
}
