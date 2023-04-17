package com.klimmenkov.spring.hibernate.lab_4.service;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties(House house);

    void saveProperty(Property tenant, House house);

    Property getProperty(int id);

    void deleteProperty(int id);

    Property getPropertyByTenant(House house, Tenant tenant);

    Property getPropertyByNumber(int number);
}
