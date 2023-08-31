package com.example.homeservicephasethree.service;


import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.Order;
import com.example.homeservicephasethree.enumeration.OrderState;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    List<Order> findByCustomerIdAndOrderState(Long customerId, OrderState orderState);
    List<Order> findBySubServiceIdAndOrderStatus(Long subServiceId, OrderState orderStateOne, OrderState orderStateTwo);
    int changeOrderStatus(Long orderId, OrderState orderState, OrderState newOrderState);

}