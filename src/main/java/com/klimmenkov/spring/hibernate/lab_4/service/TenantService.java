package com.klimmenkov.spring.hibernate.lab_4.service;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;

import java.util.List;

public interface TenantService {

    List<Tenant> getAllTenants(House house);

    void saveTenant(Tenant tenant);

    void saveRegisteredTenant(UnregisteredUser unregisteredUser, String propertyNumber, String house);

    Tenant getTenant(int id);

    void deleteTenant(int id);
}
