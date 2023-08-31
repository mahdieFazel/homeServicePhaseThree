package com.example.homeservicephasethree.entity;

import com.example.homeservicephasethree.enumeration.PersonStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

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
    Integer score;
    @ManyToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private Set<SubService> subServices = new HashSet<>();
    @OneToMany(mappedBy = "expert")
    private List<Offer> offers = new ArrayList<>();



    @Override
    public String toString() {
        return "Expert{" +
                "photo=" + Arrays.toString(photo) +
                ", score=" + score +
                '}';
    }
    @Enumerated(value = EnumType.STRING)
    private PersonStatus personStatus;

    public void addSubService(SubService subService) {
        this.subServices.add(subService);
        subService.getExperts().add(this);
    }
}
