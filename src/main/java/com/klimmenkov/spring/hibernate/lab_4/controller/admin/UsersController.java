package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.User;
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
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public String showAllUsers(Model model, @CookieValue(value = "login") String login) {
        List<User> users = userService.getAllUsers(userService.getUserByLogin(login).getHouse());
        model.addAttribute("allUsers", users);

        return "admin/all-users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());

        return "admin/add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@CookieValue(value = "login") String login,
                          @ModelAttribute("user") @Valid User user,
                          BindingResult result) {

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
}
