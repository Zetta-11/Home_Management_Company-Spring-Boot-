package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class AdminNewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    UserService userService;

    @GetMapping("/allNews")
    public String showAllNews(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("allNews", newsService.getAllNews(userService.getUserByLogin(login).getHouse()));

        return "admin/all-news";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("news", new News());

        return "admin/add-news";
    }

    @PostMapping("/addNews")
    public String addUser(@ModelAttribute("news") @Valid News news, @CookieValue(value = "login") String login, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-news";
        } else {
            newsService.saveNews(news, userService.getUserByLogin(login).getHouse());
            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allNews/{id}/remove")
    public String deleteNews(@PathVariable(value = "id") Integer id) {
        newsService.deleteNews(id);

        return "redirect:/adminPage/allNews";
    }

    @GetMapping("/allNews/{id}/edit")
    public String updateNews(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("news", newsService.getNews(id));

        return "admin/add-news";
    }
}
