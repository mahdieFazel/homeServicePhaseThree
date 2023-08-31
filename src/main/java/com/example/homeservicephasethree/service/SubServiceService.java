package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.SubService;
import com.example.homeservicephasethree.repository.SubServiceRepository;
import com.example.homeservicephasethree.service.serviceImpl.SubServiceServiceImpl;

import java.util.Optional;

public interface SubServiceService extends BaseService<SubService, Long> {
    Optional<SubService> findByName(String name);

    int deleteSubServiceById(Long subServiceId);

    int editBasePriceAndDescription(Long subServiceId, Long basePrice, String description);
}