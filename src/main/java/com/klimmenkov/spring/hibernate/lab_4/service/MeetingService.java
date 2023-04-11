package com.klimmenkov.spring.hibernate.lab_4.service;

import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Meeting;
import com.klimmenkov.spring.hibernate.lab_4.entity.MeetingDetails;

import java.util.List;

public interface MeetingService {

    List<Meeting> getAllMeetings(House house);

    void saveMeeting(Meeting meeting, House house);

    Meeting getMeeting(int id);

    MeetingDetails getMeetingDetails(Meeting meeting);

    void deleteMeeting(int id);
}
