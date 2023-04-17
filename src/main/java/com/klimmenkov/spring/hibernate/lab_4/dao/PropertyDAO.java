package com.klimmenkov.spring.hibernate.lab_4.dao;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;

import java.util.List;

public interface PropertyDAO {

    List<Property> getAllProperties(House house);

    void saveProperty(Property property, House house);

    Property getProperty(int id);

    Property getPropertyByNumber(int number);

    void deleteProperty(int id);
}
