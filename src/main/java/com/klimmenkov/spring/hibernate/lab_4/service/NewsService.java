package com.klimmenkov.spring.hibernate.lab_4.service;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.News;

import java.util.List;
import java.util.Map;

public interface NewsService {
    public List<News> getAllNews(House house);
    public Map<News, String> getNewsWithShortcuts(House house);

    public void saveNews(News news, House house);

    public News getNews(int id);

    public void deleteNews(int id);
}
