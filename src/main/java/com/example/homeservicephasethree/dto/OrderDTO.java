package com.example.homeservicephasethree.dto;

import com.example.homeservicephasethree.enumeration.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private LocalDateTime creationDate;
    private Long proposedPrice;
    private String description;
    private String subServiceName;
    private String address;
    private LocalDateTime executionTime;
    private OrderState orderState;
}
