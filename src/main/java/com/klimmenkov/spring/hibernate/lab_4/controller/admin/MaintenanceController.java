package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.service.MaintenanceService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import com.klimmenkov.spring.hibernate.lab_4.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;
    @Autowired
    UserService userService;

    @Autowired
    WorkerService workerService;

    @GetMapping("/allMaintenances")
    public String showAllMaintenances(Model model, @CookieValue(value = "login") String login) {

        model.addAttribute("allMaintenances",
                maintenanceService.getAllMaintenances(userService.getUserByLogin(login).getHouse()));

        return "admin/all-maintenances";
    }

    @GetMapping("/addMaintenance")
    public String addMaintenance(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("maintenance", new Maintenance());
        model.addAttribute("allWorkers", workerService.getAllWorkers(userService.getUserByLogin(login).getHouse()));

        return "admin/add-maintenance";
    }

    @PostMapping("/addMaintenance")
    public String addMaintenance(Model model,
                                 @CookieValue(name = "login") String login,
                                 @RequestParam(required = false) String dueDate,
                                 @RequestParam(required = false) String publicationDate,
                                 @RequestParam String isReady,
                                 @RequestParam String workerName,
                                 @ModelAttribute("maintenance") @Valid Maintenance maintenance,
                                 BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("allWorkers",
                    workerService.getAllWorkers(userService.getUserByLogin(login).getHouse()));
            return "admin/add-maintenance";
        } else if (isReady.equals("0")) {
            model.addAttribute("allWorkers",
                    workerService.getAllWorkers(userService.getUserByLogin(login).getHouse()));
            result.rejectValue("isReady", "error.isReady", "Select item from isReady dropdown list!");
            return "admin/add-maintenance";
        } else if (workerName.equals("0")) {
            model.addAttribute("allWorkers",
                    workerService.getAllWorkers(userService.getUserByLogin(login).getHouse()));
            result.rejectValue("isReady", "error.isReady", "Select item from worker dropdown list!");
            return "admin/add-maintenance";
        }

        return "admin/all-maintenances";
    }
}
