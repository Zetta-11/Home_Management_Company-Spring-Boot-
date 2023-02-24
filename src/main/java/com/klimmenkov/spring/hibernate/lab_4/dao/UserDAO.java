package com.klimmenkov.spring.hibernate.lab_4.dao;


import com.klimmenkov.spring.hibernate.lab_4.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public User getUserByLogin(String login);

    public User getUserByLoginAndPassword(String login, String pass);

    public List<User> getNullWorkerUsers();

    public List<User> getNullTenantUsers();

    public void deleteUser(int id);
}
