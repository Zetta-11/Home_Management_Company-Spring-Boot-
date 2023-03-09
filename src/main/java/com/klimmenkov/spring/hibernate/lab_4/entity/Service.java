package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;
}