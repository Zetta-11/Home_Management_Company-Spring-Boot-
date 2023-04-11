package com.klimmenkov.spring.hibernate.lab_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

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

    @NotBlank(message = "Name cannot be blank!")
    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Timestamp time;

    @NotNull(message = "Duration may not be empty!")
    @Min(value = 1, message = "Duration may be from 1 to 3 hours!")
    @Max(value = 3, message = "Duration may be from 1 to 3 hours!")
    @Column(name = "duration")
    private Integer duration;

    @OneToOne(mappedBy = "meeting", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MeetingDetails meetingDetails;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;

}