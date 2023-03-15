package com.klimmenkov.spring.hibernate.lab_4.dao;

import com.klimmenkov.spring.hibernate.lab_4.entity.Log;

import java.util.List;

public interface LogDAO {

    public List<Log> getAllLogs();

    public void writeLog(Log log);

    public Log getLog(int id);

    public void deleteLog(int id);

    public void clearAllLogs();

    public List<Log> getFilteredLogs(String login, String action);
}
