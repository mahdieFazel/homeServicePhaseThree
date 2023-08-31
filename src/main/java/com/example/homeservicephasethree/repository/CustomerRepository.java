package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Address;
import com.example.homeservicephasethree.entity.Customer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String phoneNumber);
}
