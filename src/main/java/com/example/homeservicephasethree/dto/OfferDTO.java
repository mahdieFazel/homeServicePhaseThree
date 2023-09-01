package com.example.homeservicephasethree.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

    private Long proposedPrice;
    private Integer durationTime;
    private Integer expertScore;
    private LocalDateTime endTime;
}
