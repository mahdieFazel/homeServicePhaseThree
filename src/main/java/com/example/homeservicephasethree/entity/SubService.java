package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class SubService extends BaseEntity {
    @Column(unique = true)
    private String name;
    private Long basePrice;
    private String description;

    @ManyToOne
    private HomeService homeService;
}
