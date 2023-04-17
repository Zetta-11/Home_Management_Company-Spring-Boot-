package com.klimmenkov.spring.hibernate.lab_4.controller.worker;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import com.klimmenkov.spring.hibernate.lab_4.service.MaintenanceService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import com.klimmenkov.spring.hibernate.lab_4.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workerPage")
public class WorkerController {

    @Autowired
    UserService userService;
    @Autowired
    MaintenanceService maintenanceService;
    @Autowired
    WorkerService workerService;

    @GetMapping("")
    public String getWorkerAccount() {
        return "worker/worker-account";
    }

    @GetMapping("/workerTasks")
    public String getWorkerTasks(@CookieValue(value = "login") String login, Model model) {
        House currentHouse = userService.getUserByLogin(login).getHouse();
        Worker currentWorker = workerService.getWorkerByLogin(login);
        model.addAttribute("allMaintenances", maintenanceService.getMaintenanceByWorker(currentHouse, currentWorker));

        return "worker/worker-tasks";
    }

    @PostMapping("/workerTasks/{id}/isCompleted")
    public String markMaintenanceDone(@PathVariable(value = "id") Integer id) {
        maintenanceService.setMaintenanceCompleted(id);

        return "redirect:/workerPage/workerTasks";
    }

    @GetMapping("/workerTasks/getFilteredMaintenances")
    public String getFilteredMaintenances(@CookieValue(value = "login") String login,
                                          @RequestParam(name = "rate", required = false) Integer rate,
                                          @RequestParam(name = "isReady", required = false) Character isReady,
                                          @RequestParam(name = "type", required = false) String type,
                                          Model model) {
        Worker worker = workerService.getWorkerByLogin(login);
        List<Maintenance> filteredMaintenances = maintenanceService.getFilteredMaintenances(worker, rate, isReady, type);
        model.addAttribute("allMaintenances", filteredMaintenances);

        return "worker/worker-tasks";
    }
}
