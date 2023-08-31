package com.example.homeservicephasethree.service.serviceImpl;

import com.example.homeservicephasethree.base.BaseServiceImpl;
import com.example.homeservicephasethree.entity.Offer;
import com.example.homeservicephasethree.repository.OfferRepository;
import com.example.homeservicephasethree.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class OfferServiceImpl
        extends BaseServiceImpl<Offer, Long, OfferRepository> implements OfferService {

    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }

    @Override
    public List<Offer> findByOrderIdBasedOnProposedPrice(Long orderId) { try {
        return repository.findByOrderIdBasedOnProposedPrice(orderId);
    } catch (Exception e) {
        return Collections.emptyList();
    }
    }

    @Override
    public List<Offer> findByOrderIdBasedOnExpertScore(Long orderId) {
        try {
            return repository.findByOrderIdBasedOnExpertScore(orderId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
