package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.HomeService;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeServiceRepository extends JpaRepository<HomeService, Long> {
    Optional<HomeService> findByName(String name);

    int deleteHomeServiceById(Long HomeServiceId);
}
