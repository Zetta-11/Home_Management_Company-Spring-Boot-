package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.Service;

import java.util.List;

public interface ServiceDAO {

    public List<Service> getAllServices();

    public void saveService(Service service);

    public Service getService(int id);

    public void deleteService(int id);
}
