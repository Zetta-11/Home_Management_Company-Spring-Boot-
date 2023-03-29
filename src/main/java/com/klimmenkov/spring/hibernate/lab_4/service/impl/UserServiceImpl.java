package com.klimmenkov.spring.hibernate.lab_4.service.impl;


import com.klimmenkov.spring.hibernate.lab_4.dao.UserDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public List<User> getAllUsers(House house) {
        return userDAO.getAllUsers(house);
    }

    @Override
    @Transactional
    public void saveUser(User user, House house) {
        userDAO.saveUser(user, house);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {
        return userDAO.getUserByLoginAndPassword(login, pass);
    }

    @Override
    public List<User> getNullWorkerUsers() {
        return userDAO.getNullWorkerUsers();
    }

    @Override
    public List<User> getNullTenantUsers() {
        return userDAO.getNullTenantUsers();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
