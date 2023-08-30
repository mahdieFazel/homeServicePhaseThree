package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import com.example.homeservicephasethree.enumeration.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin extends Person {
    String firstName;
    String lastName;
    @Column(unique = true)
    String username;
    @Column(nullable = false)
    String password;
    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Role role;

}
