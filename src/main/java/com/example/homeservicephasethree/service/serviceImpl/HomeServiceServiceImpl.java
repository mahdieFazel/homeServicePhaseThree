package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.HomeService;
import com.example.homeservicephasethree.repository.HomeServiceRepository;
import com.example.homeservicephasethree.service.HomeServiceService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HomeServiceServiceImpl
        extends BaseServiceImpl<HomeService, Long, HomeServiceRepository>
        implements HomeServiceService {

    public HomeServiceServiceImpl(HomeServiceRepository homeServiceRepository) {
        super(homeServiceRepository);
    }

    @Override
    public Optional<HomeService> findByName(String name) {
       try {
           return repository.findByName(name);
       }catch (Exception e){
           return Optional.empty();
       }
    }

    @Override
    public int deleteHomeServiceById(Long homeServiceId) {
        return repository.deleteHomeServiceById(homeServiceId);
    }
}
