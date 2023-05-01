package com.klimmenkov.spring.hibernate.lab_4.service;

import java.util.List;

public interface SearchService {
    List<Object[]> searchInAllTables(String keyword);

    List<Object[]> searchInOneTable(String keyword, String tableName);
}
