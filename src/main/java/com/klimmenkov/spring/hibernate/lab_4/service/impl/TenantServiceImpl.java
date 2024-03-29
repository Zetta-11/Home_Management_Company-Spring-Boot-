package com.klimmenkov.spring.hibernate.lab_4.service.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.TenantDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.HouseService;
import com.klimmenkov.spring.hibernate.lab_4.service.PropertyService;
import com.klimmenkov.spring.hibernate.lab_4.service.TenantService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantDAO tenantDAO;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    PropertyService propertyService;

    @Override
    public List<Tenant> getAllTenants(House house) {
        return tenantDAO.getAllTenants(house);
    }

    @Override
    public void saveTenant(Tenant tenant) {
        tenantDAO.saveTenant(tenant);
    }

    @Override
    public void saveRegisteredTenant(UnregisteredUser unregisteredUser, String propertyNumber, String house) {
        Tenant tenant = new Tenant();
        User linkedUser = new User();

        tenant.setName(unregisteredUser.getName());
        tenant.setSurname(unregisteredUser.getSurname());
        tenant.setPhone(unregisteredUser.getPhone());
        tenant.setProperty(propertyService.getPropertyByNumber(Integer.parseInt(propertyNumber)));

        linkedUser.setAccountType("tenant");
        linkedUser.setLogin(unregisteredUser.getLogin());
        linkedUser.setPassword(unregisteredUser.getPassword());
        linkedUser.setTenant(tenant);
        //linkedUser.setHouse(houseService.getHouse(1));

        tenant.setUser(linkedUser);

        userService.saveUser(linkedUser, houseService.getHouse(Integer.parseInt(house)));
    }

    @Override
    public Tenant getTenant(int id) {
        return tenantDAO.getTenant(id);
    }

    @Override
    public Tenant getTenantByLogin(String login) {
        return tenantDAO.getTenantByLogin(login);
    }

    @Override
    public void deleteTenant(int id) {
        tenantDAO.deleteTenant(id);
    }
}