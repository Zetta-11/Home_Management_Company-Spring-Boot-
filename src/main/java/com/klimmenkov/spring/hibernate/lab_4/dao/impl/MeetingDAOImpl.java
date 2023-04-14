package com.klimmenkov.spring.hibernate.lab_4.dao.impl;

import com.klimmenkov.spring.hibernate.lab_4.dao.MeetingDAO;
import com.klimmenkov.spring.hibernate.lab_4.entity.House;
import com.klimmenkov.spring.hibernate.lab_4.entity.Meeting;
import com.klimmenkov.spring.hibernate.lab_4.entity.MeetingDetails;
import com.klimmenkov.spring.hibernate.lab_4.entity.PaymentDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MeetingDAOImpl implements MeetingDAO {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public List<Meeting> getAllMeetings(House house) {
        Session session = manager.unwrap(Session.class);

        return session.createQuery("from Meeting m where m.house = :house", Meeting.class)
                .setParameter("house", house).getResultList();
    }

    @Override
    @Transactional
    public void saveMeeting(Meeting meeting, House house) {
        Session session = manager.unwrap(Session.class);
        meeting.setHouse(house);
        session.saveOrUpdate(meeting);
    }

    @Override
    @Transactional
    public Meeting getMeeting(int id) {
        Session session = manager.unwrap(Session.class);

        return session.get(Meeting.class, id);
    }

    @Override
    @Transactional
    public MeetingDetails getMeetingDetails(Meeting meeting) {
        Session session = manager.unwrap(Session.class);
        MeetingDetails details = (MeetingDetails) session.createQuery("from MeetingDetails d where d.id = :meetingID")
                .setParameter("meetingID", meeting.getId())
                .getSingleResult();

        return details;
    }

    @Override
    @Transactional
    public void deleteMeeting(int id) {
        Session session = manager.unwrap(Session.class);

        Query query = session.createQuery("delete from Meeting where id =:e");
        query.setParameter("e", id);
        query.executeUpdate();
    }
}
