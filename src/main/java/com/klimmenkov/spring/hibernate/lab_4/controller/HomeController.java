package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String showMainPage(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("error", error);

        return "home";
    }

    @PostMapping("/home")
    public String tryLogin(@RequestParam String login, @RequestParam String password, Model model, HttpServletResponse response) {
        User user = userService.getUserByLoginAndPassword(login, password);
        model.addAttribute("login", login);

        if (user == null) {
            model.addAttribute("error", "Incorrect login or password!");
            return "home";
        }

        Cookie cookie = new Cookie("login", login);
        Cookie isLoggedInCookie = new Cookie("isLoggedIn", "true");
        response.addCookie(cookie);

        if (user.getAccountType().equals("admin")) {

            response.addCookie(isLoggedInCookie);
            return "redirect:/adminPage";
        } else if (user.getAccountType().equals("tenant")) {
            response.addCookie(isLoggedInCookie);
            return "";
        } else {
            response.addCookie(isLoggedInCookie);
            return "";
        }
    }
}