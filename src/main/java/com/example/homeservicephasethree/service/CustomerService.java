package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.Comment;
import com.example.homeservicephasethree.entity.Customer;
import com.example.homeservicephasethree.entity.Order;

public interface CustomerService extends BaseService<Customer, Long> {
    void addCommentForExpertPerformance(Long orderId, Long expertId, Comment comment);
    int changeOrderStatusAfterStarted(Long orderId);
    void payFromCredit(Long orderId, Long customerId, Long expertId, Long amount);
    int changeOrderStatusAfterExpertComes(Long orderId);
    void addOrder(Long customerId, Long subServiceId , Order order);



}
