package com.klimmenkov.spring.hibernate.lab_4.service.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.NewsDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDAO newsDAO;

    @Override
    public List<News> getAllNews() {
        return newsDAO.getAllNews();
    }

    @Override
    public Map<News, String> getNewsWithShortcuts() {
        List<News> allNews = newsDAO.getAllNews();
        Map<News, String> shortcuts = allNews
                .stream()
                .collect(Collectors.toMap(Function.identity(), news -> {
                    String[] sentences = news.getInfo().split("\\.");
                    return sentences.length > 0 ? sentences[0] + "." : "";
                }));

        return shortcuts;
    }

    @Override
    public void saveNews(News news) {
        newsDAO.saveNews(news);
    }

    @Override
    public News getNews(int id) {
        return newsDAO.getNews(id);
    }

    @Override
    public void deleteNews(int id) {
        newsDAO.deleteNews(id);
    }
}