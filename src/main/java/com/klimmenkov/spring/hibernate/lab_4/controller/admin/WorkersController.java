package com.klimmenkov.spring.hibernate.lab_4.controller.admin;

import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import com.klimmenkov.spring.hibernate.lab_4.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/adminPage")
public class WorkersController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorkerService workerService;


    @GetMapping("/allWorkers")
    public String showAllWorkers(Model model, @CookieValue(value = "login") String login) {
        List<Worker> workers = workerService.getAllWorkers(userService.getUserByLogin(login).getHouse());
        model.addAttribute("allWorkers", workers);

        return "admin/all-workers";
    }

    @GetMapping("/addWorker")
    public String addWorker(Model model) {
        model.addAttribute("worker", new Worker());
        model.addAttribute("allUsers", userService.getNullWorkerUsers());
        model.addAttribute("allSpec", List.of("plumber", "electrician", "cleaner", "builder"));

        return "admin/add-worker";
    }

    @PostMapping("/addWorker")
    public String addWorker(Model model,
                            @RequestParam String userLogin,
                            @RequestParam String spec,
                            @ModelAttribute("worker") @Valid Worker worker,
                            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("allUsers", userService.getNullWorkerUsers());
            model.addAttribute("allSpec", List.of("plumber", "electrician", "cleaner", "builder"));

            return "admin/add-worker";
        } else {
            worker.setSpecialization(spec);
            worker.setUser(userService.getUserByLogin(userLogin));
            workerService.saveWorker(worker);

            return "redirect:/adminPage";
        }
    }
}
