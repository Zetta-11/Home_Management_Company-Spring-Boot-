package com.klimmenkov.spring.hibernate.lab_4.dao.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.PropertyDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PropertyDAOImpl implements PropertyDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Property> getAllProperties() {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Property ", Property.class).getResultList();
    }

    @Override
    @Transactional
    public void saveProperty(Property property) {
        Session session = manager.unwrap(Session.class);

        session.persist(property);
    }

    @Override
    @Transactional
    public Property getProperty(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(Property.class, id);
    }

    @Override
    @Transactional
    public Property getPropertyByNumber(int number) {
        Session session = manager.unwrap(Session.class);
        Query<Property> query = session.createQuery("from Property where number =:e", Property.class);

        Property property = query.setParameter("e", number).getSingleResultOrNull();

        return property;
    }

    @Override
    @Transactional
    public void deleteProperty(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Property where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
