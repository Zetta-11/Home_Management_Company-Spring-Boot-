package com.klimmenkov.spring.hibernate.lab_4.controller.tenant;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import com.klimmenkov.spring.hibernate.lab_4.service.TenantService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tenantPage")
public class TenantController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    TenantService tenantService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getTenantAccount() {
        return "tenant/tenant-account";
    }

    @GetMapping("myProperty")
    public String getMyProperty(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        Tenant tenant = tenantService.getTenantByLogin(login);
        Property property = propertyService.getPropertyByTenant(house, tenant);
        model.addAttribute("property", property);

        return "tenant/my-property";
    }
}
