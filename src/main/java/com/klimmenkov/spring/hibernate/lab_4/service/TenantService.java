package com.klimmenkov.spring.hibernate.lab_4.service;


import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;
import com.klimmenkov.spring.hibernate.lab_4.entity.UnregisteredUser;

import java.util.List;

public interface TenantService {

    public List<Tenant> getAllTenants();

    public void saveTenant(Tenant tenant);

    void saveRegisteredTenant(UnregisteredUser unregisteredUser);

    public Tenant getTenant(int id);

    public void deleteTenant(int id);
}
