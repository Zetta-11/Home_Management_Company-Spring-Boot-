package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.MaintenanceDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Maintenance;
import com.klimmenkov.spring.hibernate.lab_4.entity.Worker;
import com.klimmenkov.spring.hibernate.lab_4.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    MaintenanceDAO maintenanceDAO;

    @Override
    public List<Maintenance> getAllMaintenances(House house) {
        return maintenanceDAO.getAllMaintenances(house);
    }

    @Override
    public void saveMaintenance(Maintenance maintenance) {
        maintenanceDAO.saveMaintenance(maintenance);
    }

    @Override
    public Maintenance getMaintenance(int id) {
        return maintenanceDAO.getMaintenance(id);
    }

    @Override
    public void deleteMaintenance(int id) {
        maintenanceDAO.deleteMaintenance(id);
    }
}
