package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import com.example.homeservicephasethree.enumeration.PaymentWay;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity<Long> {
    @Enumerated(EnumType.STRING)
    private PaymentWay paymentWay;

    @OneToOne
    private Order order;
    @OneToOne
    private Customer customer;

    @CreationTimestamp
    private Date paymantDate;

    private String cardNumber;
}
