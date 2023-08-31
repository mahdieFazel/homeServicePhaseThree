package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.Expert;
import com.example.homeservicephasethree.entity.Offer;
import com.example.homeservicephasethree.entity.Order;
import com.example.homeservicephasethree.entity.Person;
import com.example.homeservicephasethree.enumeration.PersonStatus;
import com.example.homeservicephasethree.service.serviceImpl.ExpertServiceImpl;
import org.apache.catalina.Service;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

public interface ExpertService extends BaseService<Expert,Long> {
    Optional<Expert> findByEmail(String email);
    Optional<Expert> findByUsername(String username);
    int editPassword(Long expertId, String newPassword);
    int changeExpertStatus(Long expertId, PersonStatus personStatus);
    List<Order> showOrdersForExpert(Long expertId);
    void addOfferToOrder(Long expertId, Long orderId, Offer offer);
    int changeExpertActivation(Long expertId, Boolean isActive);
    int viewExpertScore(Long expertId);
    int updateScore(Long expertId, Integer newScore);
    void updateCredit(Long expertId, Long newCredit);



}
