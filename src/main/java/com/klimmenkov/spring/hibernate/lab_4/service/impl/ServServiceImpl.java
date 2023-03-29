package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.ServiceDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServServiceImpl implements ServService {
    @Autowired
    ServiceDAO serviceDAO;

    @Override
    public List<com.klimmenkov.spring.hibernate.lab_4.entity.Service> getAllServices(House house) {
        return serviceDAO.getAllServices(house);
    }

    @Override
    public void saveService(com.klimmenkov.spring.hibernate.lab_4.entity.Service service, House house) {
        serviceDAO.saveService(service, house);
    }

    @Override
    public com.klimmenkov.spring.hibernate.lab_4.entity.Service getService(int id) {
        return serviceDAO.getService(id);
    }

    @Override
    public void deleteService(int id) {
        serviceDAO.deleteService(id);
    }
}
