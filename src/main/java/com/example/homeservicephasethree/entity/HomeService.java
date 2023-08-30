package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity

public class HomeService extends BaseEntity {
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SubService> subServices = new HashSet<>();
}
