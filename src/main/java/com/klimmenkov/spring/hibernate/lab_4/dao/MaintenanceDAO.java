package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;

import java.util.List;

public interface MaintenanceDAO {

    List<Maintenance> getAllMaintenances(House house);

    void saveMaintenance(Maintenance maintenance);

    Maintenance getMaintenance(int id);

    void deleteMaintenance(int id);
}
