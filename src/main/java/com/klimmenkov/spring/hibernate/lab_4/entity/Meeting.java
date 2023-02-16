package com.klimmenkov.spring.hibernate.lab_4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "meetings")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Date time;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "details")
    private String details;

    @Column(name = "taken_decision_number")
    private Integer takenDecisionNumber;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;
}