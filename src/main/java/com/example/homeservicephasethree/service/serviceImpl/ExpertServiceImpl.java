package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.Expert;
import com.example.homeservicephasethree.entity.Offer;
import com.example.homeservicephasethree.entity.Order;
import com.example.homeservicephasethree.enumeration.OrderState;
import com.example.homeservicephasethree.enumeration.PersonStatus;
import com.example.homeservicephasethree.exception.*;
import com.example.homeservicephasethree.repository.ExpertRepository;
import com.example.homeservicephasethree.service.CommentService;
import com.example.homeservicephasethree.service.ExpertService;
import com.example.homeservicephasethree.service.OfferService;
import com.example.homeservicephasethree.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class ExpertServiceImpl
        extends BaseServiceImpl<Expert, Long,
        ExpertRepository> implements ExpertService {
    private final OfferService offerService;
    private final OrderService orderService;
    private final CommentService commentService;

    public ExpertServiceImpl(ExpertRepository repository,
                             OfferService offerService,
                             OrderService orderService,
                             CommentService commentService) {
        super(repository);
        this.offerService = offerService;
        this.orderService = orderService;
        this.commentService = commentService;
    }


    @Override
    public Optional<Expert> findByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Expert> findByUsername(String username) {
        try {
            return repository.findByUsername(username);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int editPassword(Long expertId, String newPassword) {
        if (findById(expertId).isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        return repository.editPassword(expertId,newPassword);
    }

    @Override
    public int changeExpertStatus(Long expertId, PersonStatus personStatus) {
        return repository.changeExpertStatus(expertId, personStatus);
    }

    @Override
    public List<Order> showOrdersForExpert(Long expertId) {
        List<Order> orders = new ArrayList<>();
        Optional<Expert> expert = findById(expertId);
        if (expert.isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        expert.get().getSubServices().forEach(subService -> {
            List<Order> filteredOrder = orderService
                    .findBySubServiceIdAndOrderStatus(
                            subService.getId(),
                            OrderState.WAITING_FOR_CHOOSING_EXPERT,
                            OrderState.WAITING_EXPERT_SUGGESTION);
            orders.addAll(filteredOrder);
        });
        return orders;
    }

    @Override
    public void addOfferToOrder(Long expertId, Long orderId, Offer offer) {
        Optional<Expert> expert = findById(expertId);
        if (expert.isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        if (!expert.get().getPersonStatus().equals(PersonStatus.CONFIRMED))
            throw new ExpertNoAccessException("the status of expert is not \"confirmed\"");
        Optional<Order> order = orderService.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("there is no order!");

        if (!expert.get().getSubServices().contains(order.get().getSubService()))
            throw new UnRelatedExpertSubServiceException("this expert is not related to this sub-service!");
        if (order.get().getSubService().getBasePrice() > offer.getProposedPrice())
            throw new PriceException("the proposed-price should not be lower than the base-price!");
        if (!order.get().getOrderState().equals(OrderState.WAITING_EXPERT_SUGGESTION))
            throw new OrderStateException("the status of this order not \"WAITING FOR EXPERT SUGGESTION\"!");

        offer.setIsAccept(false);
        offer.setExpert(expert.get());
        offer.setOrder(order.get());
        offerService.saveOrUpdate(offer);
        orderService.changeOrderStatus(orderId, OrderState.WAITING_EXPERT_SUGGESTION, OrderState.WAITING_FOR_CHOOSING_EXPERT);
    }

    @Override
    public int changeExpertActivation(Long expertId, Boolean isActive) {
        return repository.changeExpertActivation(expertId, isActive);
    }

    @Override
    public int viewExpertScore(Long expertId) {
            Optional<Expert> expert = findById(expertId);
            if (expert.isEmpty())
                throw new ExpertNotFoundException("this expert does not exist!");
            //return expert.get().getScore();
            return Math.toIntExact(expert.get().getScore());
        }

    @Override
    public int updateScore(Long expertId, Integer newScore) {
        return repository.updateScore(expertId, newScore);
    }
    @Override
    public void updateCredit(Long expertId, Long newCredit) {
        repository.updateCredit(expertId, newCredit);
    }

}
