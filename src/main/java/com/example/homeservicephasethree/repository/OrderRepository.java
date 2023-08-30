package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Order;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Id> {
}
