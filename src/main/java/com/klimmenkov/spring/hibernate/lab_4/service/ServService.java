package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Service;

import java.util.List;

public interface ServService {

    List<Service> getAllServices(House house);

    void saveService(Service service, House house);

    Service getService(int id);

    void deleteService(int id);
}
