package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.SearchDAO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SearchDAOImpl implements SearchDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Object[]> getSearchResultByKeyword(String keyword, String table) {
        Session session = manager.unwrap(Session.class);
        String sql = "SELECT * FROM (" +
                "    SELECT DISTINCT id, type, 'news' as t FROM news WHERE type LIKE :keyword AND (:table = 'ALL' OR :table = 'news')" +
                "    UNION ALL" +
                "    SELECT DISTINCT id, time, 'meetings' as t FROM meetings WHERE meetings.name LIKE :keyword AND (:table = 'ALL' OR :table = 'meetings')" +
                "    UNION ALL" +
                "    SELECT DISTINCT id, date, 'payments' as t FROM payments WHERE payment_type LIKE :keyword AND (:table = 'ALL' OR :table = 'payments')" +
                "    UNION ALL" +
                "    SELECT DISTINCT id, type, 'services' as t FROM services WHERE type LIKE :keyword AND (:table = 'ALL' OR :table = 'services')" +
                "    UNION ALL" +
                "    SELECT DISTINCT id, name, 'workers' as t FROM workers WHERE (name LIKE :keyword OR surname LIKE :keyword) AND (:table = 'ALL' OR :table = 'workers')" +
                ") AS search_results" +
                " GROUP BY id, type, t " +
                " ORDER BY id DESC";

        Query query = session.createSQLQuery(sql);
        query.setParameter("table", table);
        query.setParameter("keyword", "%" + keyword + "%");

        return query.getResultList();
    }
}
