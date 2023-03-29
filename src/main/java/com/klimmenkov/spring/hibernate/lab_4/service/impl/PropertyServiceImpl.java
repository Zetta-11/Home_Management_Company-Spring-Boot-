package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.PropertyDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Property;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyDAO propertyDAO;

    @Override
    public List<Property> getAllProperties(House house) {
        return propertyDAO.getAllProperties(house);
    }

    @Override
    public void saveProperty(Property property, House house) {
        propertyDAO.saveProperty(property, house);
    }

    @Override
    public Property getProperty(int id) {
        return propertyDAO.getProperty(id);
    }

    @Override
    public Property getPropertyByNumber(int number) {
        return propertyDAO.getPropertyByNumber(number);
    }

    @Override
    public void deleteProperty(int id) {
        propertyDAO.deleteProperty(id);
    }


}
