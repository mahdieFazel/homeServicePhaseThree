package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity<Long> {
    Double score;
    String description;

    @ManyToOne
    Customer customer;

    @ManyToOne
    Expert expert;

    @OneToOne
    Order order;
}
