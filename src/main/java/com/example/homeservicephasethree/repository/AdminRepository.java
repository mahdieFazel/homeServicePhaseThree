package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Admin;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Id> {
}
