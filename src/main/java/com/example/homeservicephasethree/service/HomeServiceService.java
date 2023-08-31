package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.HomeService;

import java.util.Optional;

public interface HomeServiceService extends BaseService<HomeService, Long> {
     Optional<HomeService> findByName(String name);
    int deleteHomeServiceById(Long homeServiceId);

}
