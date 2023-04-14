package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/adminPage")
public class AdminMainController {

    @GetMapping("")
    public String showMainPage(@CookieValue(value = "login") String login, Model model) {
        model.addAttribute("login", login);

        return "admin/admin-account";
    }

    @PostMapping("/backUpDataBase")
    public String backUpDataBase() throws IOException, InterruptedException {
        String command = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe --user=klim --password=password --port=3307  my_db " +
                "--result-file=D:\\University\\DataBases\\Lab_4\\Lab_4\\Backups_DB\\backupDB.sql";

        // execute command
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();

        // check if command executed successfully
        if (exitCode == 0) {
            System.out.println("Database backup completed successfully.");
        } else {
            System.out.println("Database backup failed.");
        }

        return "redirect:/adminPage";
    }
}
