package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.UserDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<User> getAllUsers(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from User u where u.house = :house", User.class)
                .setParameter("house", house)
                .getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        Session session = manager.unwrap(Session.class);

        session.persist(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        Session session = manager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public List<User> getNullWorkerUsers() {
        Session session = manager.unwrap(Session.class);
        //TODO
        //NEED TO CREATE QUERY LIKE IN TENANTS
        Query<User> query = session.createQuery("select u from User u left join fetch u.tenant left join fetch u.worker " +
                "where u.tenant = null and u.worker = null and u.accountType = :type", User.class);
        query.setParameter("type", "worker");

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<User> getNullTenantUsers() {
        Session session = manager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User where accountType = :type", User.class);
        query.setParameter("type", "tenant");

        List<User> users = query
                .getResultList()
                .stream()
                .filter(u -> u.getTenant() == null && u.getWorker() == null)
                .collect(Collectors.toList());

        return users;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        Session session = manager.unwrap(Session.class);
        User user;
        try {
            user = session.createQuery("from User where login = :login", User.class).setParameter("login", login).getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {
        Session session = manager.unwrap(Session.class);
        User user;
        try {
            user = session.createQuery("from User where login = :login and password = :pass", User.class)
                    .setParameter("login", login)
                    .setParameter("pass", pass)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from User where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}