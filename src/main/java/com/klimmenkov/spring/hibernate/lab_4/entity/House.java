package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<User> users;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<Property> properties;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<Meeting> meetings;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<Payment> payments;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<Service> services;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    @ToString.Exclude
    private List<News> news;
}