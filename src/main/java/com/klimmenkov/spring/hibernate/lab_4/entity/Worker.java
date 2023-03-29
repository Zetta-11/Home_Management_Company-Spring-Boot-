package com.klimmenkov.spring.hibernate.lab_4.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Field cannot be blank!")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Field cannot be blank!")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Field cannot be blank!")
    @Pattern(regexp = "^\\+?3?8?(0\\d{2}\\d{3}\\d{2}\\d{2})$", message = "Input valid phone number")
    @Column(name = "phone")
    private String phone;

//    @NotBlank(message = "Field cannot be blank!")
    @Column(name = "specialization")
    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "worker")
    private List<Maintenance> maintenances;
}
