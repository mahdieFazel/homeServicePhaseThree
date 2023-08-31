package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Offer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("""
            select o from Offer o
            where o.order.id = :orderId
            order by o.proposedPrice
            """)
    List<Offer> findByOrderIdBasedOnProposedPrice(Long orderId);

    @Query("""
            select o from Offer o
            where o.order.id = :orderId
            order by o.expert.score desc
            """)
    List<Offer> findByOrderIdBasedOnExpertScore(Long orderId);
}
