package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface MaintenanceService {

    List<Maintenance> getAllMaintenances(House house);

    List<Maintenance> getMaintenanceByWorker(House house, Worker worker);

    List<Maintenance> getFilteredMaintenances(Worker worker, Integer rate, Character isReady, String type);

    void saveMaintenance(Maintenance maintenance);

    void setMaintenanceCompleted(int id);

    Maintenance getMaintenance(int id);

    void deleteMaintenance(int id);
}
