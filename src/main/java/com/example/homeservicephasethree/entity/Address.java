package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends BaseEntity {
    String city;
    String Street;
    String postalCode;
    String zone;


}
