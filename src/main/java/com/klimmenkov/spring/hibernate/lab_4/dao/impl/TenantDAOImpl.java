package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.TenantDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TenantDAOImpl implements TenantDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Tenant> getAllTenants(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Tenant t where t.user.house = :house", Tenant.class)
                .setParameter("house", house)
                .getResultList();
    }

    @Override
    @Transactional
    public void saveTenant(Tenant tenant) {
        Session session = manager.unwrap(Session.class);

        session.persist(tenant);
    }

    @Override
    @Transactional
    public Tenant getTenant(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(Tenant.class, id);
    }

    @Override
    @Transactional
    public Tenant getTenantByLogin(String login) {
        Session session = manager.unwrap(Session.class);
        Tenant tenant = session.createQuery("from Tenant t where t.user.login = :login", Tenant.class)
                .setParameter("login", login)
                .getSingleResult();

        return tenant;
    }

    @Override
    @Transactional
    public void deleteTenant(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Tenant where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
