package com.klimmenkov.spring.hibernate.lab_4.service.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.HouseDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseDAO houseDAO;

    @Override
    public List<House> getAllHouses() {
        return houseDAO.getAllHouses();
    }

    @Override
    public void saveHouse(House house) {
        houseDAO.saveHouse(house);
    }

    @Override
    public House getHouse(int id) {
        return houseDAO.getHouse(id);
    }

    @Override
    public void deleteHouse(int id) {
        houseDAO.deleteHouse(id);
    }
}
