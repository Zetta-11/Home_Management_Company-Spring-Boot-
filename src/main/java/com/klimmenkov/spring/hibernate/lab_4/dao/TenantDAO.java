package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;

import java.util.List;

public interface TenantDAO {

    List<Tenant> getAllTenants(House house);

    void saveTenant(Tenant tenant);

    Tenant getTenant(int id);

    void deleteTenant(int id);
}
