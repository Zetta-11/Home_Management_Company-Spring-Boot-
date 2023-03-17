package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.WorkerDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import com.klimmenkov.spring.hibernate.lab_4.service.HouseService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import com.klimmenkov.spring.hibernate.lab_4.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerDAO workerDAO;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;

    @Override
    public List<Worker> getAllWorkers() {
        return workerDAO.getAllWorkers();
    }

    @Override
    public void saveWorker(Worker worker) {
        workerDAO.saveWorker(worker);
    }

    @Override
    public void saveRegisteredWorker(UnregisteredUser unregisteredUser) {
        Worker worker = new Worker();
        User linkedUser = new User();

        worker.setName(unregisteredUser.getName());
        worker.setSurname(unregisteredUser.getSurname());
        worker.setPhone(unregisteredUser.getPhone());
        worker.setSpecialization(null);

        linkedUser.setAccountType("worker");
        linkedUser.setLogin(unregisteredUser.getLogin());
        linkedUser.setPassword(unregisteredUser.getPassword());
        linkedUser.setWorker(worker);
        linkedUser.setHouse(houseService.getHouse(1));

        userService.saveUser(linkedUser);
    }

    @Override
    public Worker getWorker(int id) {
        return workerDAO.getWorker(id);
    }

    @Override
    public void deleteWorker(int id) {
        workerDAO.deleteWorker(id);
    }
}
