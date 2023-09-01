package com.example.homeservicephasethree.repository;

import com.example.homeservicephasethree.entity.Order;
import com.example.homeservicephasethree.enumeration.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    List<Order> findByCustomerIdAndOrderStatus(Long customerId, OrderState orderStatus);
    @Query("""
            from Order o
            where o.subService.id = :subServiceId
            and (o.orderState = :orderStatusOne or o.orderState = :orderStatusTwo)
            """)
    List<Order> findBySubServiceIdAndOrderStatus(Long subServiceId, OrderState orderStateOne, OrderState orderStateTwo);
    @Query("""
            update Order o
            set o.orderState = :newOrderStatus
            where o.id = :orderId and o.orderState = :orderStatus
            """)
    int changeOrderStatus(Long orderId, OrderState orderState, OrderState newOrderState);

}
