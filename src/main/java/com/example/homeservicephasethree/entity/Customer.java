package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Entity
@Data
@Getter
@Service
public class Customer extends Person {

}
