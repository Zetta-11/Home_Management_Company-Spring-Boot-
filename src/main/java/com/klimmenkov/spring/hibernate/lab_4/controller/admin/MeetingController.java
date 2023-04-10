package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Meeting;
import com.klimmenkov.spring.hibernate.lab_4.service.MeetingService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping("/adminPage")
public class MeetingController {
    @Autowired
    MeetingService meetingService;
    @Autowired
    UserService userService;

    @GetMapping("/allMeetings")
    public String showAllMeetings(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("allMeeting", meetingService.getAllMeetings(userService.getUserByLogin(login).getHouse()));

        return "admin/all-meetings";
    }

    @GetMapping("/addMaintenance")
    public String addMeeting(Model model, @CookieValue(value = "login") String login) {
        model.addAttribute("meeting", new Meeting());

        return "admin/add-meeting";
    }

    @PostMapping("/addMaintenance")
    public String addMeeting(Model model,
                                 @CookieValue(name = "login") String login,
                                 @ModelAttribute("meeting") @Valid Meeting meeting,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-meeting";
        } else if (1==1) {

            return "admin/add-meeting";
        } else {

        }
        return "redirect:/adminPage";
    }

    @PostMapping("/allMeetings/{id}/remove")
    public String deleteMeeting(@PathVariable(value = "id") Integer id) {
        meetingService.deleteMeeting(id);

        return "redirect:/adminPage/allMeetings";
    }

    @GetMapping("/allMeetings/{id}/edit")
    public String updateMeeting(@CookieValue(value = "login") String login,
                                @PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("meeting", meetingService.getMeeting(id));
        return "admin/add-meeting";
    }
}
