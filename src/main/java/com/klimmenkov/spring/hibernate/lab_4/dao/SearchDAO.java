package com.klimmenkov.spring.hibernate.lab_4.dao;

import java.util.List;

public interface SearchDAO {

    List<Object[]> getSearchResultByKeyword(String keyword, String table);
}
