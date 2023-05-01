package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.service.EmailSenderService;
import com.klimmenkov.spring.hibernate.lab_4.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/adminPage")
public class SearchController {
    @Autowired
    SearchService searchService;
    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/search")
    public String getSearchPage() {

        return "admin/search-page";
    }

    @PostMapping("/search")
    public String getSearchResults(Model model,
                                   @RequestParam("searchInput") String keyword,
                                   @RequestParam("tableSelect") String table,
                                   HttpSession session) {
        if (table.equals("all") || keyword.isBlank()) {
            model.addAttribute("allItems", searchService.searchInAllTables(keyword));
        } else {
            model.addAttribute("allItems", searchService.searchInOneTable(keyword, table));
        }
        session.setAttribute("searchInput", keyword);

        return "admin/search-page";
    }

    @PostMapping("/sendTable")
    public String sendTable(@RequestParam String emailInput, HttpSession session) throws Exception {
        String keyword = (String) session.getAttribute("searchInput");
        try {
            emailSenderService.sendEmailForSearchWithAttachment(emailInput, keyword);
        } catch (Exception ignored) {

        }
        return "redirect:/adminPage/search";
    }
}
