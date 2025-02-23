package com.balia.be.service;

import com.balia.be.domain.TCart;
import com.balia.be.web.rest.payload.request.CartListRequest;
import com.balia.be.web.rest.payload.request.CartRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing cart.
 */
@Service
public interface CartService {

    /**
     * Save a cart.
     *
     * @param cartRequest the entity to save
     * @return the persisted entity
     */
    TCart addToCart(CartListRequest cartRequest);

    TCart save(TCart tCart);
    
}
