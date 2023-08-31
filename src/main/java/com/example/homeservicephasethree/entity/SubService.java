package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubService extends BaseEntity<Long> {
    @Column(unique = true)
    private String name;
    private Long basePrice;
    private String description;

    @ManyToOne
    private HomeService homeService;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Expert> experts = new HashSet<>();
}
