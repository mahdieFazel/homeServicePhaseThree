package com.example.homeservicephasethree.dto;

import com.example.homeservicephasethree.enumeration.PersonStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertDTO {

     String firstname;
     String lastname;
     String email;
     String username;
     Integer score;
     PersonStatus personStatus;
     Long credit;
     Boolean isActive;
}
