package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Service;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.ServService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class ServicesController {
    @Autowired
    ServService service;
    @Autowired
    private UserService userService;

    @GetMapping("/allServices")
    public String showAllNews(Model model) {
        model.addAttribute("allServices", service.getAllServices());

        return "admin/all-services";
    }

    @GetMapping("/addService")
    public String addService(Model model) {
        model.addAttribute("service", new Service());

        return "admin/add-service";
    }

    @PostMapping("/addService")
    public String addService(@CookieValue(value = "login") String login,
                             @ModelAttribute("service") @Valid Service serviceForSave,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-service";
        } else {
            User admin = userService.getUserByLogin(login);
            serviceForSave.setHouse(admin.getHouse());
            service.saveService(serviceForSave);

            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allServices/{id}/remove")
    public String deleteNews(@PathVariable(value = "id") Integer id) {
        service.deleteService(id);

        return "redirect:/adminPage/allServices";
    }

    @GetMapping("/allServices/{id}/edit")
    public String updateService(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("service", service.getService(id));

        return "admin/add-service";
    }

}
