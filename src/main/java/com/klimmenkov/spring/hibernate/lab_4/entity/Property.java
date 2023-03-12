package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "Field may not be empty!")
    @Size(min = 1, message = "Enter correct number!")
    @Column(name = "number")
    private Integer number;

    @NotNull(message = "Field may not be empty!")
    @Size(min = 1, max = 300, message = "Enter correct square!")
    @Column(name = "square")
    private Integer square;

    @NotNull(message = "Field may not be empty!")
    @Size(min = 1, max = 50, message = "Enter correct floor!")
    @Column(name = "floor")
    private Integer floor;

    @NotNull(message = "Field may not be empty!")
    @Size(min = 1, max = 15, message = "Enter correct number!")
    @Column(name = "rooms_quantity")
    private Integer roomsQuantity;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;

    @OneToMany(mappedBy = "property")
    private List<Tenant> tenants;

}