package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.*;
import com.example.homeservicephasethree.enumeration.OrderState;
import com.example.homeservicephasethree.enumeration.PersonStatus;
import com.example.homeservicephasethree.exception.*;
import com.example.homeservicephasethree.repository.AdminRepository;
import com.example.homeservicephasethree.service.*;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
@Service
public class AdminServiceImpl
        extends BaseServiceImpl<Admin, Long, AdminRepository> implements AdminService {
    private final HomeServiceService homeServiceService;
    private final SubServiceService subServiceService;
    private final ExpertService expertService;
    private final OfferService offerService;
    private final OrderService orderService;
    private final CustomerService customerService;
    public AdminServiceImpl(AdminRepository repository,
                            HomeServiceService homeServiceService,
                            SubServiceService subServiceService,
                            ExpertService expertService,
                            OfferService offerService,
                            OrderService orderService,
                            CustomerService customerService) {
        super(repository);
        this.homeServiceService = homeServiceService;
        this.subServiceService = subServiceService;
        this.expertService = expertService;
        this.offerService = offerService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @Override
    public void addHomeService(HomeService homeService) {
        if (homeServiceService.findByName(homeService.getName()).isPresent())
            throw new DuplicateHomeServiceException("this Service is already exist");
        homeServiceService.saveOrUpdate(homeService);
    }

    @Override
    public int deleteHomeService(Long homeServiceId) {
        if (homeServiceService.findById(homeServiceId).isEmpty())
            throw new HomeServiceNotFoundException("this service is not exist");
        return homeServiceService.deleteHomeServiceById(homeServiceId);
    }

    @Override
    public void addSubService(SubService subService, Long homeServiceId) {
        Optional<HomeService> mainService = homeServiceService.findById(homeServiceId);
        if (mainService.isEmpty())
            throw new HomeServiceNotFoundException("this  service dose not exist!");
        if (subServiceService.findByName(subService.getName()).isPresent())
            throw new DuplicateSubServiceException("this subService already exist!");
        subServiceService.saveOrUpdate(subService);
    }


    @Override
    public int deleteSubService(Long subServiceId) {
        if (subServiceService.findById(subServiceId).isEmpty())
            throw new SubServiceNotFoundException("this subService dose not exist!");
        return subServiceService.deleteSubServiceById(subServiceId);
    }

    @Override
    public void addExpertToSubService(Long subServiceId, Long expertId) { Optional<SubService> subService = subServiceService.findById(subServiceId);
        if (subService.isEmpty()) {
            throw new SubServiceNotFoundException("this sub-service dose not exist!");
        }
        Optional<Expert> expert = expertService.findById(expertId);
        if (expert.isEmpty()) {
            throw new ExpertNotFoundException("this expert does not exist!");
        } else if (!expert.get().getPersonStatus().equals(PersonStatus.CONFIRMED)) {
            throw new ExpertNoAccessException("the status of expert is not \"confirmed\"");
        }
        expert.get().addSubService(subService.get());
        expertService.saveOrUpdate(expert.get());
    }

    @Override
    public int editSubServiceBasePriceAndDescription(Long subServiceId, Long basePrice, String description) {
        int affectedRows = subServiceService.editBasePriceAndDescription(subServiceId, basePrice, description);
        if (affectedRows == 0)
            throw new SubServiceNotFoundException("this sub-service dose not exist!");
        return affectedRows;
    }

    @Override
    public int changeExpertState(Long expertId, PersonStatus expertStatus) {
        if (expertService.findById(expertId).isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        return expertService.changeExpertStatus(expertId, expertStatus);
    }
    @Override
    public boolean checkExpertDelayForDoingWork(Long offerId) {
        Optional<Offer> offer = offerService.findById(offerId);
        if (offer.isEmpty())
            throw new OfferNotFoundException("there is no offers!");
        Order order = offer.get().getOrder();
        if (!order.getOrderStatus().equals(OrderState.PAID))
            throw new OrderStatusException("the status of this order is not yet \"PAID\"!");
        Optional<Expert> expert = expertService.findById(offer.get().getExpert().getId());
        if (expert.isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        if (order.getUpdateTime().compareTo(offer.get().getEndTime()) > 0) {
            long timeDifference = ChronoUnit.HOURS.between(offer.get().getEndTime(), order.getUpdateTime());
            expert.get().setScore(expert.get().getScore() - (int) timeDifference);
            expertService.saveOrUpdate(expert.get());
            return true;
        }
        return false;
    }

    @Override
    public int changeExpertActivation(Long expertId, Boolean isActive) {
        Optional<Expert> expert = expertService.findById(expertId);
        if (expert.isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        if (expert.get().getScore() > 0 | expert.get().getIsActive().equals(false))
            throw new ExpertActivationException("the expert score is either positive or the expert account is inactive");
        return expertService.changeExpertActivation(expertId, isActive);
    }
}
