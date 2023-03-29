package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public String showMainPage(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("unregisteredUser", new UnregisteredUser());
        //model.addAttribute("allProperties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));
        model.addAttribute("allSpecializations", Arrays.asList("plumber", "electrician", "cleaner", "builder"));
        model.addAttribute("allHouses", houseService.getAllHouses());

        model.addAttribute("properties", new ArrayList<>());

        return "registration";
    }

    @PostMapping("/registration")
    public String registrateUser(Model model, @CookieValue(value = "login") String login,
                                 @RequestParam String propertyNumber,
                                 @RequestParam String specialization,
                                 @RequestParam String house,
                                 @ModelAttribute("unregisteredUser") @Valid UnregisteredUser unregisteredUser,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return returnLastValuesToForm(login, model, unregisteredUser);
        } else if (!unregisteredUser.getPassword().equals(unregisteredUser.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "error.user", "Passwords are not equal!");
            return returnLastValuesToForm(login, model, unregisteredUser);
        } else if (userService.getUserByLogin(unregisteredUser.getLogin()) != null) {
            result.rejectValue("login", "error.user", "Such user already exists!");
            return returnLastValuesToForm(login, model, unregisteredUser);
        } else if ((propertyNumber.equals("0") && unregisteredUser.getRad().equals("tenant"))
                || (specialization.equals("0") && unregisteredUser.getRad().equals("worker"))) {
            result.rejectValue("rad", "error.user", "Select from dropdown list!");
            return returnLastValuesToForm(login, model, unregisteredUser);
        } else if (house.equals("0")) {
            result.rejectValue("rad", "error.user", "Select from dropdown list!");
            return returnLastValuesToForm(login, model, unregisteredUser);
        } else {
            if (unregisteredUser.getRad().equals("tenant")) {
                tenantService.saveRegisteredTenant(unregisteredUser, propertyNumber, house);
            } else {
                workerService.saveRegisteredWorker(unregisteredUser, specialization, house);
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/properties/{houseId}")
    @ResponseBody
    public List<Property> getPropertiesByHouseId(@PathVariable("houseId") String houseId) {
        return propertyService.getAllProperties(houseService.getHouse(Integer.parseInt(houseId)));
    }

    private String returnLastValuesToForm(String login, Model model, @ModelAttribute("unregisteredUser") @Valid UnregisteredUser user) {
        model.addAttribute("restoredLogin", user.getLogin());
        model.addAttribute("restoredPassword", user.getPassword());
        model.addAttribute("restoredPhone", user.getPhone());
        model.addAttribute("restoredName", user.getName());
        model.addAttribute("restoredSurName", user.getSurname());
        model.addAttribute("allProperties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));
        model.addAttribute("allSpecializations", Arrays.asList("plumber", "electrician", "cleaner", "builder"));
        model.addAttribute("allHouses", houseService.getAllHouses());

        return "registration";
    }
}

