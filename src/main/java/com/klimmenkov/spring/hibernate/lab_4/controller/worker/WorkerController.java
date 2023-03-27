package com.klimmenkov.spring.hibernate.lab_4.controller.worker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workerPage")
public class WorkerController {

    @GetMapping("")
    public String getWorkerAccount(){
        return "worker/worker-account";
    }
}
