package com.klimmenkov.spring.hibernate.lab_4.dao.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.NewsDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<News> getAllNews() {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from News ", News.class).getResultList();
    }

    @Override
    @Transactional
    public void saveNews(News news) {
        Session session = manager.unwrap(Session.class);
        session.persist(news);
    }

    @Override
    @Transactional
    public News getNews(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(News.class, id);
    }

    @Override
    @Transactional
    public void deleteNews(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from News where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}