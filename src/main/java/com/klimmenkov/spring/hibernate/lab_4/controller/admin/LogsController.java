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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
                                   @RequestParam String action,
                                   @RequestParam(required = false) String startDate,
                                   @RequestParam(required = false) String endDate) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;

        if (!startDate.equals("")) {
            start = dateFormat.parse(startDate);
        }
        if (!endDate.equals("")) {
            end = dateFormat.parse(endDate);
        }
        if (userLogin.equals("0") && action.equals("0") && startDate.equals("") && endDate.equals("")) {
            model.addAttribute("allLogs", logService.getAllLogs());
        } else {
            model.addAttribute("allLogs", logService.getFilteredLogs(userLogin, action, start, end));
        }
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allActions", Arrays.asList("get", "delete", "save"));
        model.addAttribute("startTime", startDate);
        model.addAttribute("endTime", endDate);

        return "admin/all-logs";
    }

    @PostMapping("/clearLogs")
    public String deleteAllLogs() {
        logService.clearAllLogs();

        return "redirect:/adminPage/allLogs";
    }

}
