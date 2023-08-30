package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Admin;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);

    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
