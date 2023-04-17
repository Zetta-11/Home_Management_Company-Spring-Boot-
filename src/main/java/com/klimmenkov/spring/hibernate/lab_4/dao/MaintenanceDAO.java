package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface MaintenanceDAO {

    List<Maintenance> getAllMaintenances(House house);

    List<Maintenance> getMaintenanceByWorker(House house, Worker worker);

    void saveMaintenance(Maintenance maintenance);

    Maintenance getMaintenance(int id);

    List<Maintenance> getFilteredMaintenances(Worker worker, Integer rate, Character isReady, String type);

    void saveMaintenanceCompleted(int id);

    void deleteMaintenance(int id);
}
