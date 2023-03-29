package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import com.klimmenkov.spring.hibernate.lab_4.service.TenantService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/adminPage")
public class TenantsController {
    @Autowired
    private UserService userService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/allTenants")
    public String showAllTenants(Model model, @CookieValue(value = "login") String login) {
        List<Tenant> tenants = tenantService.getAllTenants(userService.getUserByLogin(login).getHouse());
        model.addAttribute("allTenants", tenants);

        return "admin/all-tenants";
    }

    @GetMapping("allTenants/{id}/property")
    public String getProperty(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("properties", propertyService.getProperty(id));

        return "admin/all-properties";
    }

    @GetMapping("/addTenant")
    public String addTenant(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("tenant", new Tenant());
        model.addAttribute("allProperties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));
        model.addAttribute("allUsers", userService.getNullTenantUsers());

        return "admin/add-tenant";
    }

    @PostMapping("/addTenant")
    public String addTenant(Model model, @CookieValue(value = "login") String login,
                            @RequestParam Integer propertyNumber,
                            @RequestParam String userLogin,
                            @ModelAttribute("tenant") @Valid Tenant tenant,
                            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("allProperties", propertyService.getAllProperties(userService.getUserByLogin(login).getHouse()));
            model.addAttribute("allUsers", userService.getNullTenantUsers());

            return "admin/add-tenant";
        } else {
            tenant.setUser(userService.getUserByLogin(userLogin));
            tenant.setProperty(propertyService.getPropertyByNumber(propertyNumber));

            tenantService.saveTenant(tenant);

            return "redirect:/adminPage";
        }
    }
}
