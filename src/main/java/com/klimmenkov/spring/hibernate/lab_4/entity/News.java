package com.klimmenkov.spring.hibernate.lab_4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Field cannot be blank!")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Field cannot be blank!")
    @Column(name = "info")
    private String info;

    @ManyToOne
    @JoinColumn(name = "propery_house_id", referencedColumnName = "id")
    private House house;
}