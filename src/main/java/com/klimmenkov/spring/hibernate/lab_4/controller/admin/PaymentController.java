package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;
import com.klimmenkov.spring.hibernate.lab_4.service.PaymentService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/adminPage")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;

    @GetMapping("/allPayments")
    public String showAllPayments(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("allPayments", paymentService.getAllPayments(userService.getUserByLogin(login).getHouse()));
        model.addAttribute("incomesSum", paymentService.getSumOfAvailableMoney());
        model.addAttribute("allUsers", userService.getAllUsers(userService.getUserByLogin(login).getHouse()));

        return "admin/all-payments";
    }

    @GetMapping("allPayments/{id}/details")
    public String getPaymentDetails(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("details", paymentService.getPaymentDetails(paymentService.getPayment(id)));

        return "tenant/payment-details";
    }

    @GetMapping("allPayments/getFilteredPaymentsByType")
    public String getFilteredPaymentsByType(@CookieValue(value = "login") String login,
                                            Model model,
                                            @RequestParam String paymentType) {
        if (paymentType.equals("0")) {
            model.addAttribute("allPayments", paymentService.getAllPayments(userService.getUserByLogin(login).getHouse()));
        } else {
            model.addAttribute("allPayments",
                    paymentService.getFilteredPaymentsByType(userService.getUserByLogin(login).getHouse(), paymentType));
        }
        model.addAttribute("incomesSum", paymentService.getSumOfAvailableMoney());
        model.addAttribute("allUsers", userService.getAllTenantUsers(userService.getUserByLogin(login).getHouse()));

        return "admin/all-payments";
    }

    @GetMapping("allPayments/getFilteredPaymentsByLogin")
    public String getFilteredPaymentsByTenant(@CookieValue(value = "login") String login,
                                              Model model,
                                              @RequestParam String userLogin) {
        if (userLogin.equals("0")) {
            model.addAttribute("allPayments", paymentService.getAllPayments(userService.getUserByLogin(login).getHouse()));
        } else {
            model.addAttribute("allPayments",
                    paymentService.getFilteredPaymentsByTenant(userService.getUserByLogin(login).getHouse(), userLogin));
        }
        model.addAttribute("incomesSum", paymentService.getSumOfAvailableMoney());
        model.addAttribute("allUsers", userService.getAllTenantUsers(userService.getUserByLogin(login).getHouse()));
        return "admin/all-payments";
    }

    @GetMapping("/addPayment")
    public String addPayment(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("incomesSum", paymentService.getSumOfAvailableMoney());

        return "admin/add-payment";
    }

    @PostMapping("/addPayment")
    public String addPayment(Model model,
                             @CookieValue(name = "login") String login,
                             @RequestParam String details,
                             @RequestParam(required = false) String date,
                             @ModelAttribute("payment") @Valid Payment payment,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-payment";
        } else if (date.equals("")) {
            result.rejectValue("sum", "error.sum", "Date may not be empty!");
            return "admin/add-payment";
        } else if (details.equals("")) {
            result.rejectValue("sum", "error.sum", "Details may not be empty!");
            return "admin/add-payment";
        } else if (payment.getPaymentType().equals("expenses") && payment.getSum() > paymentService.getSumOfAvailableMoney()) {
            result.rejectValue("sum", "error.sum", "Expenses sum must be lower than all money!");
            model.addAttribute("incomesSum", paymentService.getSumOfAvailableMoney());
            return "admin/add-payment";
        } else {
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setDetails(details);
            paymentDetails.setPayment(payment);

            payment.setPaymentDetails(paymentDetails);
            paymentService.savePayment(payment, userService.getUserByLogin(login).getHouse());

            return "redirect:/adminPage";
        }
    }

    @PostMapping("/allPayments/{id}/remove")
    public String deletePayment(@PathVariable(value = "id") Integer id) {
        paymentService.deletePayment(id);

        return "redirect:/adminPage/allPayments";
    }
}
