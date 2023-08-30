package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.base.BaseEntity;
import com.example.homeservicephasethree.enumeration.PersonStatus;
import com.example.homeservicephasethree.enumeration.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person extends BaseEntity<Long>  {
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String password;

    @Enumerated(EnumType.STRING)
    PersonStatus personStatus;

    @CreationTimestamp
    Date registeredDate;
    String credit;

    @Enumerated(EnumType.STRING)
    Role role;
}
