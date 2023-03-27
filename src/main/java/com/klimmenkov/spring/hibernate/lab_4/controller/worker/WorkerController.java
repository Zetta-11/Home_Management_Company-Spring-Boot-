package com.klimmenkov.spring.hibernate.lab_4.controller.worker;

import com.klimmenkov.spring.hibernate.lab_4.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workerPage")
public class WorkerController {

    @Autowired
    NewsService newsService;

    @GetMapping("")
    public String getWorkerAccount() {
        return "worker/worker-account";
    }

    @GetMapping("/workerTasks")
    public String getWorkerTasks(@CookieValue(value = "login") String login) {
        return "worker/worker-tasks";
    }

}
