
package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.Order;
import com.example.homeservicephasethree.enumeration.OrderState;
import com.example.homeservicephasethree.repository.OrderRepository;
import com.example.homeservicephasethree.service.OrderService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service

public class OrderServiceImpl
        extends BaseServiceImpl<Order, Long, OrderRepository> implements OrderService {
    private EntityManager entityManager;

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<Order> findByCustomerId(Long customerId) {
        try {
            return repository.findByCustomerId(customerId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Order> findByCustomerIdAndOrderState(Long customerId, OrderState orderStatus) {
        try {
            return repository.findByCustomerIdAndOrderStatus(customerId, orderStatus);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Order> findBySubServiceIdAndOrderStatus(Long subServiceId, OrderState orderStateOne, OrderState orderStateTwo) {
        try {
            return repository.findBySubServiceIdAndOrderStatus(subServiceId, orderStateOne, orderStateTwo);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public int changeOrderStatus(Long orderId, OrderState orderState, OrderState newOrderState) {
        return repository.changeOrderStatus(orderId, orderState, newOrderState);
    }
}
