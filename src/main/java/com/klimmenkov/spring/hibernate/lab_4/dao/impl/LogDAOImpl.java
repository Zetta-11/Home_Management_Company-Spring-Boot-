package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.LogDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LogDAOImpl implements LogDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Log> getAllLogs() {
        Session session = manager.unwrap(Session.class);
        List<Log> allLogs = session.createQuery("from Log ", Log.class).getResultList();

        return allLogs;
    }

    @Override
    @Transactional
    public void writeLog(Log log) {
        Session session = manager.unwrap(Session.class);

        session.persist(log);
    }

    @Override
    @Transactional
    public Log getLog(int id) {
        Session session = manager.unwrap(Session.class);
        Log log = session.get(Log.class, id);

        return log;
    }

    @Override
    @Transactional
    public void deleteLog(int id) {
        Session session = manager.unwrap(Session.class);

        Query<Log> query = session.createQuery("delete from Log where id =:e", Log.class);
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
