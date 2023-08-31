package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import com.example.homeservicephasethree.enumeration.OfferState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends BaseEntity<Long> {
    private Boolean isAccept;
    private LocalDateTime endTime;
    @ManyToOne
    private Expert expert;
    @CreationTimestamp
    private Date registrationDate;
    private Long proposedPrice;
    private Double durationOfWork;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Enumerated(EnumType.STRING)
    private OfferState offerStatus;
    @ManyToOne
    private Order order;
    private String description;
}
