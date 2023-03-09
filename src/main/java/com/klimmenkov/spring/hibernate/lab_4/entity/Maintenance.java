package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "maintenances")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Date publicationDate;

    private Date dueDate;

    private String type;

    private String details;

    private Integer rate;

    private Boolean isReady;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private Worker worker;

}