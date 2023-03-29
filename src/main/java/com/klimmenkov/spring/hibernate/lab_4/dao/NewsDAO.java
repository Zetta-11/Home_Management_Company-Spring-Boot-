package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.News;

import java.util.List;

public interface NewsDAO {

    public List<News> getAllNews(House house);

    public void saveNews(News news, House house);

    public News getNews(int id);

    public void deleteNews(int id);
}
