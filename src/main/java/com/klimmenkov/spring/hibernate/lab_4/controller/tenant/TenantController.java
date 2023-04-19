package com.klimmenkov.spring.hibernate.lab_4.controller.tenant;

import com.klimmenkov.spring.hibernate.lab_4.entity.*;
import com.klimmenkov.spring.hibernate.lab_4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tenantPage")
public class TenantController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    TenantService tenantService;
    @Autowired
    UserService userService;
    @Autowired
    ServService servService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    PaymentService paymentService;

    @GetMapping("")
    public String getTenantAccount() {
        return "tenant/tenant-account";
    }

    @GetMapping("/myProperty")
    public String getMyProperty(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        Tenant tenant = tenantService.getTenantByLogin(login);
        Property property = propertyService.getPropertyByTenant(house, tenant);
        model.addAttribute("property", property);

        return "tenant/my-property";
    }

    @GetMapping("/services")
    public String getServices(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        List<Service> allServices = servService.getAllServices(house);
        model.addAttribute("allServices", allServices);

        return "tenant/services";
    }

    @GetMapping("/meetings")
    public String getMeetings(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        List<Meeting> allMeetings = meetingService.getAllMeetingsOrderedByTime(house);
        model.addAttribute("allMeetings", allMeetings);

        return "tenant/meetings";
    }

    @GetMapping("meetings/{id}/details")
    public String getMeetingDetails(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("details", meetingService.getMeetingDetails(meetingService.getMeeting(id)));

        return "tenant/meeting-details";
    }

    @GetMapping("/payments")
    public String getPayments() {

        return "tenant/payments";
    }

    @GetMapping("/payments/reports")
    public String getPaymentReports(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        model.addAttribute("allPayments", paymentService.getAllPayments(house));

        return "tenant/reports";
    }

    @GetMapping("/payments/reports/{id}/details")
    public String getPaymentDetails(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("details", paymentService.getPaymentDetails(paymentService.getPayment(id)));

        return "tenant/payment-details";
    }

    @GetMapping("/payments/reports/getOwnReports")
    public String getOwnReports(@CookieValue(value = "login") String login, Model model) {
        House house = userService.getUserByLogin(login).getHouse();
        model.addAttribute("allPayments", paymentService.getFilteredPaymentsByTenant(house, login));

        return "tenant/reports";
    }

    @GetMapping("/payments/pay")
    public String getPayPage() {
        return "tenant/pay";
    }
}
