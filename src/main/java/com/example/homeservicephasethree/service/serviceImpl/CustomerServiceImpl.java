package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.*;
import com.example.homeservicephasethree.enumeration.OrderState;
import com.example.homeservicephasethree.exception.*;
import com.example.homeservicephasethree.repository.CustomerRepository;
import com.example.homeservicephasethree.service.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl
        extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {
    private final HomeServiceService homeServiceService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;
    private final OfferService offerService;
    private final ExpertService expertService;
    private final CommentService commentService;

    private EntityManager entityManager;
    public CustomerServiceImpl(CustomerRepository repository,
                               HomeServiceService homeServiceService,
                               SubServiceService subServiceService,
                               OrderService orderService,
                               OfferService offerService,
                               ExpertService expertService,
                               EntityManager entityManager) {
        super(repository);
        this.homeServiceService = homeServiceService;
        this.subServiceService = subServiceService;
        this.orderService = orderService;
        this.offerService = offerService;
        this.expertService = expertService;
        this.entityManager = entityManager;
    }


    @Override
    public void addCommentForExpertPerformance(Long orderId, Long expertId, Comment comment) {
        if (comment.getScore() == null)
            throw new NullScoreException("The score cannot be null!");
        if (comment.getDescription() == null)
            throw new NullCommentException("The comment cannot be null!");
        if (comment.getScore() < 0 && comment.getScore() > 6)
            throw new ScoreException("the expert score should be between 1 and 5!");
        Optional<Order> order = orderService.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("there is no orders!");
        Optional<Expert> expert = expertService.findById(expertId);
        if (expert.isEmpty())
            throw new CustomerNotFoundException("this expert does not exist!");
        comment.setOrder(order.get());
        commentService.saveOrUpdate(comment);
        expertService.updateScore(expertId, (int) (expert.get().getScore() + comment.getScore()));
    }
    }

    @Override
    public int changeOrderStatusAfterStarted(Long orderId) {
        Optional<Order> order = orderService.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("this order does not exist!");
        if (!order.get().getOrderStatus().equals(OrderState.STARTED))
            throw new OrderStateException("the status of this order is not yet \"STARTED\"!");
        return orderService.changeOrderStatus(orderId, OrderState.STARTED, OrderStatus.DONE);
    }

    @Override
    public void payFromCredit(Long orderId, Long customerId, Long expertId, Long amount) {
        Optional<Order> order = orderService.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("this order does not exist!");
        if (!order.get().getOrderStatus().equals(OrderState.DONE))
            throw new OrderStateException("the status of this order is not yet \"DONE\"!");
        Optional<Customer> customer = findById(customerId);
        if (customer.isEmpty())
            throw new CustomerNotFoundException("this customer does not exit!");
        if (customer.get().getCredit() < amount)
            throw new InsufficientFoundsException("Insufficient founds!");
        Optional<Expert> expert = expertService.findById(expertId);
        if (expert.isEmpty())
            throw new ExpertNotFoundException("this expert does not exist!");
        orderService.changeOrderStatus(orderId, OrderState.DONE, OrderState.PAID);
        updateCredit(customerId, customer.get().getCredit() - amount);
        expertService.updateCredit(expertId, expert.get().getCredit() + (long) (amount * 0.7));
    }
    @Override
    public int changeOrderStatusAfterExpertComes(Long orderId) {
        Optional<Order> order = orderService.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("this order does not exist!");
        if (!order.get().getOrderStatus().equals(OrderState.WAITING_FOR_EXPERT_TO_COME))
            throw new OrderStateException("the status of this order is not yet \"WAITING FOR EXPERT TO COME\"!");
        return orderService.changeOrderStatus(orderId, OrderState.WAITING_FOR_EXPERT_TO_COME, OrderState.STARTED);
    }

    @Override
    public void addOrder(Long customerId, Long subServiceId, Order order) {
        Optional<Customer> customer = findById(customerId);
        if (customer.isEmpty())
            throw new CustomerNotFoundException("there is no customers!");
        Optional<SubService> subService = subServiceService.findById(subServiceId);
        if (subService.isEmpty())
            throw new SubServiceNotFoundException("this sub-service dose not exist!");
        order.setOrderStatus(OrderState.WAITING_EXPERT_SUGGESTION);
        order.setCustomer(customer.get());
        order.setSubService(subService.get());
        orderService.saveOrUpdate(order);
    }

}
}
