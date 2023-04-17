package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.WorkerDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WorkerDAOImpl implements WorkerDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Worker> getAllWorkers(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Worker w where w.user.house = :house ", Worker.class)
                .setParameter("house", house)
                .getResultList();
    }

    @Override
    @Transactional
    public void saveWorker(Worker worker) {
        Session session = manager.unwrap(Session.class);
        session.persist(worker);
    }

    @Override
    @Transactional
    public Worker getWorker(int id) {
        Session session = manager.unwrap(Session.class);
        return session.get(Worker.class, id);
    }

    @Override
    public Worker getWorkerByName(String name, House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Worker w where w.user.house = :house and w.name = :name", Worker.class)
                .setParameter("house", house)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Worker getWorkerByLogin(String login) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Worker w where w.user.login = :login", Worker.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void deleteWorker(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Worker where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
