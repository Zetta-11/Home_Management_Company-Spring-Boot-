package com.klimmenkov.spring.hibernate.lab_4.dao;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;

import java.util.List;

public interface HouseDAO {

    public List<House> getAllHouses();

    public void saveHouse(House house);

    public House getHouse(int id);

    public void deleteHouse(int id);
}
