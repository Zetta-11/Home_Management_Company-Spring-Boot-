package com.klimmenkov.spring.hibernate.lab_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

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

    @Min(value = 1, message = "Enter correct sum!")
    @Max(value = 100_000, message = "Enter correct sum!")
    @NotNull(message = "Field may not be empty!")
    @Column(name = "sum")
    private Integer sum;

    @Column(name = "date")
    private Timestamp date;

    @Pattern(regexp = "^income$|^expenses|^worker$", message = "Payment type can be only: income, expenses!")
    @Column(name = "payment_type")
    private String paymentType;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PaymentDetails paymentDetails;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "property_house_id", referencedColumnName = "id")
    private House house;
}
