package com.example.homeservicephasethree.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Expert extends Person{
    @Lob
    @Column(columnDefinition = "BLOB" , length = 300)
     byte[] photo;
    Double score;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<SubService> subServices = new ArrayList<>();

    @Override
    public String toString() {
        return "Expert{" +
                "photo=" + Arrays.toString(photo) +
                ", score=" + score +
                '}';
    }
}
