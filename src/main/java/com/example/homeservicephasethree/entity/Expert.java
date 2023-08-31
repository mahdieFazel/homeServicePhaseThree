package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.enumeration.PersonStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Expert extends Person{
    @Lob
    @Column(columnDefinition = "BLOB" , length = 300)
     byte[] photo;
    Long score;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<SubService> subServices = new ArrayList<>();

    @Override
    public String toString() {
        return "Expert{" +
                "photo=" + Arrays.toString(photo) +
                ", score=" + score +
                '}';
    }
    @Enumerated(value = EnumType.STRING)
    private PersonStatus personStatus;
}
