package com.klimmenkov.spring.hibernate.lab_4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tenants")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tenant {

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

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
}