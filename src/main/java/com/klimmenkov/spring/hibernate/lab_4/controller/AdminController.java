package com.klimmenkov.spring.hibernate.lab_4.controller;

import com.klimmenkov.spring.hibernate.lab_4.entity.News;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private LogService logService;

    @GetMapping("")
    public String showMainPage(@CookieValue(value = "login") String login, Model model) {
        model.addAttribute("login", login);

        return "admin/admin-account";
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);

        return "admin/all-users";
    }

    @GetMapping("/allTenants")
    public String showAllTenants(Model model) {
        List<Tenant> tenants = tenantService.getAllTenants();
        model.addAttribute("allTenants", tenants);

        return "admin/all-tenants";
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
            model.addAttribute("allProperties", propertyService.getAllProperties());
            model.addAttribute("allUsers", userService.getNullTenantUsers());
            return "admin/add-property";
        } else if (propertyService.getPropertyByNumber(property.getNumber()) != null) {
            result.rejectValue("number", "error.property", "Same property already exists!");
            return "admin/add-property";
        } else {
            property.setHouse(userService.getUserByLogin(login).getHouse());
            propertyService.saveProperty(property);

            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allProperties/{id}/remove")
    public String deleteProperty(@PathVariable(value = "id") Integer id) {
        propertyService.deleteProperty(id);

        return "redirect:/adminPage/allProperties";
    }

    @GetMapping("/allProperties")
    public String showAllProperties(Model model) {
        model.addAttribute("properties", propertyService.getAllProperties());

        return "admin/all-properties";
    }

    @GetMapping("allTenants/{id}/property")
    public String getProperty(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("properties", propertyService.getProperty(id));

        return "admin/all-properties";
    }

    @GetMapping("/addTenant")
    public String addTenant(Model model) {
        model.addAttribute("tenant", new Tenant());
        model.addAttribute("allProperties", propertyService.getAllProperties());
        model.addAttribute("allUsers", userService.getNullTenantUsers());

        return "admin/add-tenant";
    }

    @PostMapping("/addTenant")
    public String addTenant(Model model,
                            @RequestParam Integer propertyNumber,
                            @RequestParam String userLogin,
                            @ModelAttribute("tenant") @Valid Tenant tenant,
                            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("allProperties", propertyService.getAllProperties());
            model.addAttribute("allUsers", userService.getNullTenantUsers());

            return "admin/add-tenant";
        } else {
            tenant.setUser(userService.getUserByLogin(userLogin));
            tenant.setProperty(propertyService.getPropertyByNumber(propertyNumber));

            tenantService.saveTenant(tenant);

            return "redirect:/adminPage";
        }
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());

        return "admin/add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@CookieValue(value = "login") String login, @ModelAttribute("user") @Valid User user, BindingResult result) {

        User addingUser = userService.getUserByLogin(user.getLogin());

        if (result.hasErrors()) {
            return "admin/add-user";
        } else if (addingUser != null) {
            result.rejectValue("login", "error.user", "An account already exists for this email.");
            return "admin/add-user";
        } else {
            User admin = userService.getUserByLogin(login);
            user.setHouse(admin.getHouse());
            user.setAccountType("admin");
            userService.saveUser(user);

            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allUsers/{id}/remove")
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);

        return "redirect:/adminPage/allUsers";
    }

    @GetMapping("/allNews")
    public String showAllNews(Model model) {
        model.addAttribute("allNews", newsService.getAllNews());

        return "admin/all-news";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("news", new News());

        return "admin/add-news";
    }

    @PostMapping("/addNews")
    public String addUser(@ModelAttribute("news") @Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-news";
        } else {
            newsService.saveNews(news);
            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allNews/{id}/remove")
    public String deleteNews(@PathVariable(value = "id") Integer id) {
        newsService.deleteNews(id);

        return "redirect:/adminPage/allNews";
    }

    @PostMapping("/allLogs")
    public String showAllLogs(Model model) {
        model.addAttribute("allLogs", logService.getAllLogs());

        return "admin/all-logs";
    }
}
