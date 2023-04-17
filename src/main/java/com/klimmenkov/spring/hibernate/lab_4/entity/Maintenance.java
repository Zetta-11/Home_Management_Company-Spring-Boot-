package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

    @Column(name = "publication_date")
    private Timestamp publicationDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @NotBlank(message = "Should be not blank!")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Should be not blank!")
    @Column(name = "details")
    private String details;

    @NotNull(message = "Field may not be empty!")
    @Min(value = 1, message = "Enter correct rate!" )
    @Max(value = 5, message = "Enter correct rate!")
    @Column(name = "rate")
    private Integer rate;

    //@NotNull(message = "Field is ready may not be empty!")
    @Column(name = "is_ready")
    private Character isReady;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private Worker worker;

}