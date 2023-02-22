package com.klimmenkov.spring.hibernate.lab_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showMainPage(Model model) {
        model.addAttribute("Home", "Home page");

        return "home";
    }

}
