package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface WorkerDAO {

    List<Worker> getAllWorkers(House house);

    void saveWorker(Worker worker);

    Worker getWorker(int id);

    void deleteWorker(int id);

}
