package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Address;
import com.example.homeservicephasethree.entity.Customer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
