package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogService {

    public List<Log> getAllLogs();

    public void saveLog(Log log);

    public Log getLog(int id);

    public void deleteLog(int id);

    public void clearAllLogs();

    public List<Log> getFilteredLogs(String login, String action, java.util.Date startDate, Date endDate);
}
