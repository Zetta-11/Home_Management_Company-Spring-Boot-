package com.klimmenkov.spring.hibernate.lab_4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer number;

    private Integer square;

    private Integer floor;

    private Integer roomsQuantity;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;

    @OneToMany(mappedBy = "property")
    private List<Tenant> tenants;

}
