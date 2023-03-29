package com.klimmenkov.spring.hibernate.lab_4.service;


import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.User;

import java.util.List;

public interface UserService {

     List<User> getAllUsers(House house);

     void saveUser(User user, House house);

     User getUser(int id);

     User getUserByLogin(String login);

     User getUserByLoginAndPassword(String login, String pass);

     List<User> getNullWorkerUsers();

     List<User> getNullTenantUsers();

     void deleteUser(int id);
}
