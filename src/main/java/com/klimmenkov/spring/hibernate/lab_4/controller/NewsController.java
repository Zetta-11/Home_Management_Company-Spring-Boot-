package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/newsPage")
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;

    @Autowired
    PropertyService propertyService;

    @GetMapping("")
    public String getNews(Model model) {
        model.addAttribute("allNews", newsService.getNewsWithShortcuts());
        model.addAttribute("newsQuantity", newsService.getAllNews().size());
        model.addAttribute("usersQuantity", userService.getAllUsers().size());
        model.addAttribute("propertiesQuantity", propertyService.getAllProperties().size());
        model.addAttribute("currentDate", LocalDate.now());

        return "news-page";
    }

    @GetMapping("/{id}/read")
    public String readCurrentNews(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("news", newsService.getNews(id));

        return "current-news";
    }
}
