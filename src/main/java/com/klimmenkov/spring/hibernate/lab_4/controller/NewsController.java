package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newsPage")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("")
    public String getNews(Model model) {
        model.addAttribute("allNews", newsService.getAllNews());

        return "news-page";
    }
}
