package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class PropertiesController {
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/allProperties")
    public String showAllProperties(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("properties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));

        return "admin/all-properties";
    }

    @GetMapping("/addProperty")
    public String addProperty(Model model) {
        model.addAttribute("property", new Property());

        return "admin/add-property";
    }

    @PostMapping("/addProperty")
    public String addProperty(@CookieValue(name = "login") String login,
                              Model model,
                              @ModelAttribute("property") @Valid Property property,
                              BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("allProperties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));
            model.addAttribute("allUsers", userService.getNullTenantUsers());
            return "admin/add-property";
        } else if (propertyService.getPropertyByNumber(property.getNumber()) != null) {
            result.rejectValue("number", "error.property", "Same property already exists!");
            return "admin/add-property";
        } else {
            property.setHouse(userService.getUserByLogin(login).getHouse());
            propertyService.saveProperty(property, userService.getUserByLogin(login).getHouse());

            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allProperties/{id}/remove")
    public String deleteProperty(@PathVariable(value = "id") Integer id) {
        propertyService.deleteProperty(id);

        return "redirect:/adminPage/allProperties";
    }
}
