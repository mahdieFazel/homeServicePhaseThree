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
            and (o.orderStatus = :orderStatusOne or o.orderStatus = :orderStatusTwo)
            """)
    List<Order> findBySubServiceIdAndOrderStatus(Long subServiceId, OrderState orderStateOne, OrderState orderStateTwo);
    @Query("""
            update Order o
            set o.orderStatus = :newOrderStatus
            where o.id = :orderId and o.orderStatus = :orderStatus
            """)
    int changeOrderStatus(Long orderId, OrderState orderState, OrderState newOrderState);

}
