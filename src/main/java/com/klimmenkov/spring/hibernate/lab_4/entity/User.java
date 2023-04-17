package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "You must enter a correct email address")
    @Column(name = "login")
    private String login;

    @Pattern(regexp = "^.*(?=.{8,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "password must contain at " +
            "least 1 upper and lower case symbol, one digit and must be longer 8 symbols")
    @Column(name = "password")
    private String password;

    @Pattern(regexp = "^admin$|^tenant$|^worker$", message = "Role can be only: admin, tenant, worker")
    @Column(name = "account_type")
    private String accountType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Tenant tenant;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    @JsonIgnore
    private House house;
}