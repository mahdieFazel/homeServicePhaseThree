package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.Offer;

import java.util.List;

public interface OfferService extends BaseService<Offer, Long> {
    List<Offer> findByOrderIdBasedOnProposedPrice(Long orderId);

    List<Offer> findByOrderIdBasedOnExpertScore(Long orderId);

}
