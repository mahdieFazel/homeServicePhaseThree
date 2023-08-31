package com.example.homeservicephasethree.service;

import com.example.homeservicephasethree.base.BaseService;
import com.example.homeservicephasethree.entity.Admin;
import com.example.homeservicephasethree.entity.HomeService;
import com.example.homeservicephasethree.entity.SubService;
import com.example.homeservicephasethree.enumeration.PersonStatus;

public interface AdminService extends BaseService<Admin, Long> {
    void addHomeService (HomeService homeService);
    int deleteHomeService(Long homeServiceId);
    void addSubService(SubService subService, Long homeServiceId);
    int deleteSubService(Long subServiceId);
    void addExpertToSubService(Long subServiceId, Long expertId);
    int editSubServiceBasePriceAndDescription(Long subServiceId, Long basePrice, String description);
    int changeExpertState(Long expertId, PersonStatus expertStatus);
    boolean checkExpertDelayForDoingWork(Long offerId);
    int changeExpertActivation(Long expertId, Boolean isActive);





}
