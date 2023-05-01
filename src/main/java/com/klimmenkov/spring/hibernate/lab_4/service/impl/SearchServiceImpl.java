package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.SearchDAO;
import com.klimmenkov.spring.hibernate.lab_4.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchDAO searchDAO;

    @Override
    public List<Object[]> searchInAllTables(String keyword) {
        List<Object[]> results = new ArrayList<>();
        results.addAll(searchDAO.getSearchResultByKeyword(keyword, "news"));
        results.addAll(searchDAO.getSearchResultByKeyword(keyword, "meetings"));
        results.addAll(searchDAO.getSearchResultByKeyword(keyword, "payments"));
        results.addAll(searchDAO.getSearchResultByKeyword(keyword, "services"));
        results.addAll(searchDAO.getSearchResultByKeyword(keyword, "workers"));

        return results;
    }

    @Override
    public List<Object[]> searchInOneTable(String keyword, String tableName) {
        return searchDAO.getSearchResultByKeyword(keyword, tableName);
    }
}
