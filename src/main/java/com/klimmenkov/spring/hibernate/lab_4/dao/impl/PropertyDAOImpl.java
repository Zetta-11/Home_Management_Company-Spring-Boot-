package com.klimmenkov.spring.hibernate.lab_4.dao.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.PropertyDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PropertyDAOImpl implements PropertyDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Property> getAllProperties(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Property p where p.house = :house", Property.class)
                .setParameter("house", house).getResultList();
    }

    @Override
    @Transactional
    public void saveProperty(Property property, House house) {
        Session session = manager.unwrap(Session.class);
        property.setHouse(house);
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
        Property property;
        try {
            property = session.createQuery("from Property where number =:e", Property.class)
                    .setParameter("e", number).getSingleResult();
            return property;
        } catch (Exception e) {
            return null;
        }
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
