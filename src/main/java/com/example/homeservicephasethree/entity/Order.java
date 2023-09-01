package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import com.example.homeservicephasethree.enumeration.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(nullable = false)
    private SubService subService;
    private Long proposedPrice;
    @Column(length = 300)
    private String jobDescription;
    @CreationTimestamp
    private Date orderRegistrationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfWorkPerformed;
    @OneToOne
    @Transient
    private Address address;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Offer> offers = new HashSet<>();
    @ManyToOne
    private Expert expert;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
