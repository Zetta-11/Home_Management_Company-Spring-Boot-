package com.klimmenkov.spring.hibernate.lab_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "payment_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @Column(name = "details")
//    @NotBlank(message = "Should be not blank!")
    private String details;

    @OneToOne
    @MapsId
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
