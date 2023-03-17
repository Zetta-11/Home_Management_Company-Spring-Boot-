package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    void saveWorker(Worker worker);

    void saveRegisteredWorker(UnregisteredUser unregisteredUser, String specialization);

    Worker getWorker(int id);

    void deleteWorker(int id);
}
