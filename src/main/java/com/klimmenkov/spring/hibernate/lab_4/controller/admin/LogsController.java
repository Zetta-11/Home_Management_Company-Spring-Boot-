package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminPage")
public class LogsController {
    @Autowired
    private LogService logService;

    @GetMapping("/allLogs")
    public String showAllLogs(Model model) {
        model.addAttribute("allLogs", logService.getAllLogs());

        return "admin/all-logs";
    }
}
