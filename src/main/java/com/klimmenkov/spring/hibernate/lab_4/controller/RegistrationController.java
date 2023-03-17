package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class RegistrationController {
    @Autowired
    TenantService tenantService;
    @Autowired
    WorkerService workerService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;

    @Autowired
    PropertyService propertyService;

    @GetMapping("/registration")
    public String showMainPage(Model model) {
        model.addAttribute("unregisteredUser", new UnregisteredUser());
        model.addAttribute("allProperties", propertyService.getAllProperties());
        model.addAttribute("allSpecializations", Arrays.asList("plumber", "electrician", "cleaner", "builder"));

        return "registration";
    }

    @PostMapping("/registration")
    public String registrateUser(Model model,
                                 @RequestParam String propertyNumber,
                                 @RequestParam String specialization,
                                 @ModelAttribute("unregisteredUser") @Valid UnregisteredUser unregisteredUser,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return returnLastValuesToForm(model, unregisteredUser);
        } else if (!unregisteredUser.getPassword().equals(unregisteredUser.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "error.user", "Passwords are not equal!");
            return returnLastValuesToForm(model, unregisteredUser);
        } else if (userService.getUserByLogin(unregisteredUser.getLogin()) != null) {
            result.rejectValue("login", "error.user", "Such user already exists!");
            return returnLastValuesToForm(model, unregisteredUser);
        } else if ((propertyNumber.equals("0") && unregisteredUser.getRad().equals("tenant"))
                || (specialization.equals("0") && unregisteredUser.getRad().equals("worker"))) {
            result.rejectValue("rad", "error.user", "Select from dropdown list!");
            return returnLastValuesToForm(model, unregisteredUser);
        } else {
            if (unregisteredUser.getRad().equals("tenant")) {
                tenantService.saveRegisteredTenant(unregisteredUser, propertyNumber);
            } else {
                workerService.saveRegisteredWorker(unregisteredUser, specialization);
            }
        }
        return "redirect:/home";
    }

    private String returnLastValuesToForm(Model model, @ModelAttribute("unregisteredUser") @Valid UnregisteredUser user) {
        model.addAttribute("restoredLogin", user.getLogin());
        model.addAttribute("restoredPassword", user.getPassword());
        model.addAttribute("restoredPhone", user.getPhone());
        model.addAttribute("restoredName", user.getName());
        model.addAttribute("restoredSurName", user.getSurname());
        model.addAttribute("allProperties", propertyService.getAllProperties());
        model.addAttribute("allSpecializations", Arrays.asList("plumber", "electrician", "cleaner", "builder"));

        return "registration";
    }
}

