package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
//TODO
@Controller
@RequestMapping("/adminPage")
public class MeetingController {

    @GetMapping("/allMeetings")
    public String showAllMeetings(Model model, @CookieValue(value = "login") String login) {


        return "admin/all-meetings";
    }
}
