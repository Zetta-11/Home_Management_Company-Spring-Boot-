package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;

import java.util.List;

public interface MaintenanceService {

    List<Maintenance> getAllMaintenances(House house);

    void saveMaintenance(Maintenance maintenance);

    Maintenance getMaintenance(int id);

    void deleteMaintenance(int id);
}
