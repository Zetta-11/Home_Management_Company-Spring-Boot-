package com.klimmenkov.spring.hibernate.lab_4.dao;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;

import java.util.List;

public interface PropertyDAO {

    public List<Property> getAllProperties(House house);

    public void saveProperty(Property property, House house);

    public Property getProperty(int id);

    public Property getPropertyByNumber(int number);

    public void deleteProperty(int id);
}
