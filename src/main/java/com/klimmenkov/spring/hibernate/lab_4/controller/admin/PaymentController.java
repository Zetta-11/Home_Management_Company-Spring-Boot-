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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        model.addAttribute("incomesSum", paymentService.getSumOfIncomePayments());

        return "admin/all-payments";
    }

    @GetMapping("allPayments/{id}/details")
    public String getPaymentDetails(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("details", paymentService.getPaymentDetails(paymentService.getPayment(id)));

        return "admin/payment-details";
    }

    @GetMapping("/addPayment")
    public String addPayment(Model model) {
        model.addAttribute("payment", new Payment());

        return "admin/add-payment";
    }

    @PostMapping("/addPayment")
    public String addPayment(@CookieValue(name = "login") String login,
                             @RequestParam String details,
                             @RequestParam(required = false) String date,
                             @ModelAttribute("payment") @Valid Payment payment,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-payment";
        } else if(date.equals("")){
            result.rejectValue("sum", "error.sum", "Date may not be empty!");
            return "admin/add-payment";
        } else if (details.equals("")) {
            result.rejectValue("sum", "error.sum", "Details may not be empty!");
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
