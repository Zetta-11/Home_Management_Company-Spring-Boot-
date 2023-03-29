package com.klimmenkov.spring.hibernate.lab_4.service;



import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;

import java.util.List;

public interface PropertyService {

    public List<Property> getAllProperties(House house);

    public void saveProperty(Property tenant, House house);

    public Property getProperty(int id);

    public void deleteProperty(int id);

    public Property getPropertyByNumber(int number);
}
