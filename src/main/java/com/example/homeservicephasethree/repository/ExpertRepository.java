package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Expert;
import com.example.homeservicephasethree.enumeration.PersonStatus;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
    Optional<Expert> findByEmail(String email);

    Optional<Expert> findByUsername(String phoneNumber);
    @Query("""
            update Expert e
            set e.password = :newPassword
            where e.id = :expertId
            """)
    int editPassword(Long expertId, String newPassword);
    @Query("""
            update Expert e
            set e.personStatus = :expertStatus
            where e.id = :expertId
            """)
    int changeExpertStatus(Long expertId, PersonStatus personStatus);

    @Query("""
            update Expert e
            set e.isActive = :isActive
            where e.id = :expertId
            """)
    int changeExpertActivation(Long expertId, Boolean isActive);
    @Query("""
            update Expert e
            set e.score = :newScore
            where e.id = :expertId
            """)
    int updateScore(Long expertId, Integer newScore);



}
