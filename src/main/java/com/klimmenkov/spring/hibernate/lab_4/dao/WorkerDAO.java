package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface WorkerDAO {

    public List<Worker> getAllWorkers();

    public void saveWorker(Worker worker);

    public Worker getWorker(int id);

    public void deleteWorker(int id);

}
