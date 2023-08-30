package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Address;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByPostalCode(String postalCode);

    Optional<Address> findById(Integer addressId);
}
