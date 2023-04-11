package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Meeting;
import com.klimmenkov.spring.hibernate.lab_4.entity.MeetingDetails;
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
        model.addAttribute("allMeetings", meetingService.getAllMeetings(userService.getUserByLogin(login).getHouse()));

        return "admin/all-meetings";
    }

    @GetMapping("allMeetings/{id}/details")
    public String getMeetingDetails(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("details", meetingService.getMeetingDetails(meetingService.getMeeting(id)));

        return "admin/meeting-details";
    }

    @GetMapping("/addMeeting")
    public String addMeeting(Model model) {
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("meetingDetails", new MeetingDetails());

        return "admin/add-meeting";
    }

    @PostMapping("/addMeeting")
    public String addMeeting(@CookieValue(name = "login") String login,
                             @RequestParam String details,
                             @RequestParam String decisionNumber,
                             @ModelAttribute("meeting") @Valid Meeting meeting,
                             BindingResult result) {
        Integer number;
        try {
            number = Integer.parseInt(decisionNumber);
        } catch (NumberFormatException e) {
            number = null;
        }

        if (result.hasErrors()) {
            return "admin/add-meeting";
        } else if (number == null) {
            result.rejectValue("duration", "error.duration", "Decision number is incorrect!");
            return "admin/add-meeting";
        } else if (details.isBlank()) {
            result.rejectValue("duration", "error.duration", "Details is incorrect!");
            return "admin/add-meeting";
        } else {
            if (meeting.getId() != null) {
                meetingService.deleteMeeting(meeting.getId());
                meeting.setId(null);
            }
            MeetingDetails meetingDetails = new MeetingDetails();
            meetingDetails.setTakenDecisionNumber(number);
            meetingDetails.setDetails(details);
            meetingDetails.setMeeting(meeting);

            meeting.setMeetingDetails(meetingDetails);
            meetingService.saveMeeting(meeting, userService.getUserByLogin(login).getHouse());

            return "redirect:/adminPage/allMeetings";
        }
    }

    @PostMapping("/allMeetings/{id}/remove")
    public String deleteMeeting(@PathVariable(value = "id") Integer id) {
        meetingService.deleteMeeting(id);

        return "redirect:/adminPage/allMeetings";
    }

    @GetMapping("/allMeetings/{id}/edit")
    public String updateMeeting(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("meeting", meetingService.getMeeting(id));
        return "admin/add-meeting";
    }
}
