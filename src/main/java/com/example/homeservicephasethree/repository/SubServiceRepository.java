package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.SubService;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    Optional<SubService> findByName(String name);

    int deleteSubServiceById(Long subServiceId);

}