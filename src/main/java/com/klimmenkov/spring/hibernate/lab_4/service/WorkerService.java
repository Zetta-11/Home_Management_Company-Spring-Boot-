package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers(House house);

    void saveWorker(Worker worker);

    void saveRegisteredWorker(UnregisteredUser unregisteredUser, String specialization, String house);

    Worker getWorker(int id);

    Worker getWorkerByName(String name, House house);

    Worker getWorkerByLogin(String login);

    void deleteWorker(int id);
}
