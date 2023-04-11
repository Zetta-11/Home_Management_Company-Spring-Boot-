package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.MeetingDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Meeting;
import com.klimmenkov.spring.hibernate.lab_4.entity.MeetingDetails;
import com.klimmenkov.spring.hibernate.lab_4.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    MeetingDAO meetingDAO;

    @Override
    public List<Meeting> getAllMeetings(House house) {
        return meetingDAO.getAllMeetings(house);
    }

    @Override
    public void saveMeeting(Meeting meeting, House house) {
        meetingDAO.saveMeeting(meeting, house);
    }

    @Override
    public Meeting getMeeting(int id) {
        return meetingDAO.getMeeting(id);
    }

    @Override
    public MeetingDetails getMeetingDetails(Meeting meeting) {
        return meetingDAO.getMeetingDetails(meeting);
    }

    @Override
    public void deleteMeeting(int id) {
        meetingDAO.deleteMeeting(id);
    }
}
