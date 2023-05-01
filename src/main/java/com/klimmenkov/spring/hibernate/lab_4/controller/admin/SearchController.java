package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminPage")
public class SearchController {

    @GetMapping("/search")
    public String getSearchPage() {

        return "admin/search-page";
    }

    @PostMapping("/search")
    public String getSearchResults() {

        return "admin/search-page";
    }
}
