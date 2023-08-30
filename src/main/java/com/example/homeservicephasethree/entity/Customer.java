package com.example.homeservicephasethree.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Entity
@Data
@Getter
@Service
public class Customer extends Person {

}
