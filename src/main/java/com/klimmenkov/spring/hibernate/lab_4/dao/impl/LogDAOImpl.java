package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.LogDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.Log;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @Transactional
    public void clearAllLogs() {
        Session session = manager.unwrap(Session.class);

        NativeQuery query = session.createSQLQuery("truncate table log");
        query.executeUpdate();
    }

    @Override
    public List<Log> getFilteredLogs(String login, String action, java.util.Date startDate, Date endDate) {
        Session session = manager.unwrap(Session.class);
        String query = "from Log where 1=1 ";
        Map<String, Object> params = new HashMap<>();
        List<Log> allLogs = null;

        if (!login.equals("0")) {
            query += "and user like :login ";
            params.put("login", login + "%");
        }
        if (!action.equals("0")) {
            query += "and action like :action ";
            params.put("action", action + "%");
        }
        if (startDate != null) {
            query += "and date >= :startDate ";
            params.put("startDate", startDate);
        }
        if (endDate != null) {
            query += "and date <= :endDate ";
            params.put("endDate", endDate);
        }

        Query<Log> q = session.createQuery(query, Log.class);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
        }
        allLogs = q.getResultList();

        return allLogs;
    }
}
