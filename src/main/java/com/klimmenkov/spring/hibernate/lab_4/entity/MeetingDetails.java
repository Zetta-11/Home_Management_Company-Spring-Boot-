package com.klimmenkov.spring.hibernate.lab_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meeting_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MeetingDetails {

    @Id
    @Column(name = "meeting_id")
    private Integer id;

    @Column(name = "details")
    @NotBlank(message = "Should be not blank!")
    private String details;

    @Column(name = "taken_decision_number")
    @NotNull(message = "Should be not null!")
    private Integer takenDecisionNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
