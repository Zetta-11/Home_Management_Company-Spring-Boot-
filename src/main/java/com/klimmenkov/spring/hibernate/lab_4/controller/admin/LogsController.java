package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.service.LogService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("/adminPage")
public class LogsController {
    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @GetMapping("/allLogs")
    public String showAllLogs(Model model) {
        model.addAttribute("allLogs", logService.getAllLogs());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allActions", Arrays.asList("get", "delete", "add"));

        return "admin/all-logs";
    }

    @GetMapping("/getFilteredLogs")
    public String showFilteredLogs(Model model,
                                   @RequestParam String userLogin,
                                   @RequestParam String action) {
        if (!userLogin.equals("0") && !action.equals("0")) {
            model.addAttribute("allLogs", logService.getFilteredLogs(userLogin, action));
            model.addAttribute("allUsers", userService.getAllUsers());
            model.addAttribute("allActions", Arrays.asList("get", "delete", "add"));
            return "admin/all-logs";
        }

        model.addAttribute("allLogs", logService.getAllLogs());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allActions", Arrays.asList("get", "delete", "save"));

        return "admin/all-logs";
    }

    @PostMapping("/clearLogs")
    public String deleteAllLogs() {
        logService.clearAllLogs();

        return "redirect:/adminPage/allLogs";
    }

}
