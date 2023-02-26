package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.Tenant;

import java.util.List;

public interface TenantDAO {

    public List<Tenant> getAllTenants();

    public void saveTenant(Tenant tenant);

    public Tenant getTenant(int id);

    public void deleteTenant(int id);
}
