package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/allNews")
    public String showAllNews(Model model) {
        model.addAttribute("allNews", newsService.getAllNews());

        return "admin/all-news";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("news", new News());

        return "admin/add-news";
    }

    @PostMapping("/addNews")
    public String addUser(@ModelAttribute("news") @Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-news";
        } else {
            newsService.saveNews(news);
            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allNews/{id}/remove")
    public String deleteNews(@PathVariable(value = "id") Integer id) {
        newsService.deleteNews(id);

        return "redirect:/adminPage/allNews";
    }
}
