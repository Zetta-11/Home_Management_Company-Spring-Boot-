package com.klimmenkov.spring.hibernate.lab_4.service;



import com.klimmenkov.spring.hibernate.lab_4.entity.Property;

import java.util.List;

public interface PropertyService {

    public List<Property> getAllProperties();

    public void saveProperty(Property tenant);

    public Property getProperty(int id);

    public void deleteProperty(int id);

    public Property getPropertyByNumber(int number);
}
