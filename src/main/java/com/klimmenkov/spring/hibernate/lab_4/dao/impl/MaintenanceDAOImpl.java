package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.MaintenanceDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MaintenanceDAOImpl implements MaintenanceDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Maintenance> getAllMaintenances(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Maintenance m where m.worker.user.house = :house", Maintenance.class)
                .setParameter("house", house).getResultList();
    }

    @Override
    @Transactional
    public List<Maintenance> getMaintenanceByWorker(House house, Worker worker) {
        Session session = manager.unwrap(Session.class);

        List<Maintenance> maintenancesByWorker = session.createQuery("from Maintenance m " +
                        "where m.worker.user.house = :house " +
                        "and m.worker = :worker", Maintenance.class)
                .setParameter("house", house)
                .setParameter("worker", worker)
                .getResultList();

        return maintenancesByWorker;
    }

    @Override
    @Transactional
    public void saveMaintenance(Maintenance maintenance) {
        Session session = manager.unwrap(Session.class);
        session.saveOrUpdate(maintenance);
    }

    @Override
    @Transactional
    public Maintenance getMaintenance(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(Maintenance.class, id);
    }

    @Override
    public List<Maintenance> getFilteredMaintenances(Worker worker, Integer rate, Character isReady, String type) {
        Session session = manager.unwrap(Session.class);
        String queryString = "FROM Maintenance m WHERE m.worker = :worker";
        Map<String, Object> params = new HashMap<>();
        List<Maintenance> maintenances;

        if (rate != null) {
            queryString += " AND m.rate = :rate ";
            params.put("rate", rate);
        }
        if (!isReady.equals('0')) {
            queryString += " AND m.isReady = :isReady ";
            params.put("isReady", isReady);
        }
        if (type != null && !type.isEmpty()) {
            queryString += " AND m.type LIKE :type ";
            params.put("type", type + "%");
        }
        params.put("worker", worker);
        Query<Maintenance> query = session.createQuery(queryString, Maintenance.class);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        maintenances = query.getResultList();

        return maintenances;
    }

    @Override
    @Transactional
    public void setMaintenanceCompleted(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("update Maintenance m set m.isReady = :isReady where m.id = :id");
        query.setParameter("isReady", 'c');
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteMaintenance(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Maintenance where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
