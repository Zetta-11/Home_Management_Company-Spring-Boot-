package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotBlank(message = "Field may not be empty!")
    @Column(name = "type")
    private String type;

    @Column(name = "phone")
    @NotBlank(message = "Field may not be empty!")
    @Pattern(regexp = "^\\+?3?8?(0\\d{2}\\d{3}\\d{2}\\d{2})$", message = "Input valid phone number")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;
}