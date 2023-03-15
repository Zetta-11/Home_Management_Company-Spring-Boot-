package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.LogDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.Log;
import com.klimmenkov.spring.hibernate.lab_4.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDAO logDAO;
    @Override
    public List<Log> getAllLogs() {
        return logDAO.getAllLogs();
    }

    @Override
    public void saveLog(Log log) {
        logDAO.writeLog(log);
    }

    @Override
    public Log getLog(int id) {
        return logDAO.getLog(id);
    }

    @Override
    public void deleteLog(int id) {
        logDAO.deleteLog(id);
    }

    @Override
    public void clearAllLogs() {
        logDAO.clearAllLogs();
    }

    @Override
    public List<Log> getFilteredLogs(String login, String action) {
        return logDAO.getFilteredLogs(login, action);
    }

}
