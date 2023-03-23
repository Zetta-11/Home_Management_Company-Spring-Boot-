package com.klimmenkov.spring.hibernate.lab_4.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "date")
    private Date date;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PaymentDetails paymentDetails;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;
}