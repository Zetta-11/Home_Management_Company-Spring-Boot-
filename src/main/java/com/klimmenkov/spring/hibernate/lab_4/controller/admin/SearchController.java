package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/adminPage")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public String getSearchPage() {

        return "admin/search-page";
    }

    @PostMapping("/search")
    public String getSearchResults(Model model,
                                   @RequestParam("searchInput") String keyword,
                                   @RequestParam("tableSelect") String table) {
        if (table.equals("ALL") || keyword.isBlank()) {
            model.addAttribute("allItems", searchService.searchInAllTables(keyword));
        } else {
            model.addAttribute("allItems", searchService.searchInOneTable(keyword, table));
        }
        return "admin/search-page";
    }
}
