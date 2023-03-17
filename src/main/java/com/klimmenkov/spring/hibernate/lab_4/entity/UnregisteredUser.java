package com.klimmenkov.spring.hibernate.lab_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UnregisteredUser {

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

    @NotBlank()
    @Pattern(regexp = "^\\+?3?8?(0\\d{2}\\d{3}\\d{2}\\d{2})$", message = "Input valid phone number")
    private String phone;

    @Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Enter a correct email address")
    @Column(name = "login")
    private String login;

    @Pattern(regexp = "^.*(?=.{8,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Enter correct password!")
    private String password;

    @Pattern(regexp = "^.*(?=.{8,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Enter correct password!")
    private String repeatPassword;

    @NotBlank(message = "Field cannot be blank!")
    private String rad;

}
