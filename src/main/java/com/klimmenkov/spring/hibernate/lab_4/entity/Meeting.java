package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Field may not be empty!")
    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Date time;

    @NotNull(message = "Field may not be empty!")
    @Column(name = "duration")
    private Integer duration;

    @OneToOne(mappedBy = "meeting", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MeetingDetails meetingDetails;

    @NotNull(message = "Field may not be empty!")
    @Column(name = "taken_decision_number")
    private Integer takenDecisionNumber;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;

}