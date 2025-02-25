package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MProduct;
import com.balia.be.domain.MUser;
import com.balia.be.domain.TCart;
import com.balia.be.domain.TCartProduct;
import com.balia.be.repository.MProductRepository;
import com.balia.be.repository.TCartRepository;
import com.balia.be.service.CartService;
import com.balia.be.service.UserService;
import com.balia.be.web.rest.payload.request.CartListRequest;
import com.balia.be.web.rest.payload.request.CartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    TCartRepository tCartRepository;

    @Autowired
    MProductRepository productRepository;

    @Autowired
    UserService userService;

    /**
     * Save a cart.
     *
     * @param cartRequest the entity to save
     * @return the persisted entity
     */
    @Override
    public TCart addToCart(CartListRequest cartRequest) throws RuntimeException {
        log.debug("Request to save cartRequest : {}", cartRequest);

        TCart cart = null;
        String username = SecurityUtils.getCurrentUserLogin();        

        if (cartRequest != null) {

            if (cartRequest.getId() != null) {
                cart = tCartRepository.findById(cartRequest.getId()).orElseThrow(() 
                        -> new RuntimeException("Cart not found"));
            } else {
                cart = new TCart();
            }

            MProduct product = productRepository.findById(cartRequest.getmProduct().getId()).orElseThrow(() 
                    -> new RuntimeException("Product not found"));

            // Check stock availability
            if (product.getStock() < cartRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock available");
            }

            // Find if item already exists in cart
            Optional<TCartProduct> existingItem = cart.getCartItems().stream()
                    .filter(item -> Objects.equals(item.getmProduct().getId(), cartRequest.getmProduct().getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                if (product.getStock() < (existingItem.get().getQty() + cartRequest.getQuantity())) {
                    throw new RuntimeException("Not enough stock available");
                }
                existingItem.get().setQty(existingItem.get().getQty() + cartRequest.getQuantity());
            } else {
                TCartProduct newItem = new TCartProduct();
                newItem.setTcart(cart);
                newItem.setmProduct(product);
                newItem.setQty(cartRequest.getQuantity());
                newItem.setPrice(Double.valueOf(product.getPrice()));
                newItem.setSpecialPrice(calculateDiscount(product));
                newItem.setCurrency(product.getCurrency());
                newItem.setCreatedBy(username);
                newItem.setCreatedDate(new Date());

                cart.getCartItems().add(newItem);
                cart.setCurrency(product.getCurrency());
                cart.setCreatedBy(username);
                cart.setCreatedDate(new Date());
                cart.setmUser(userService.getUserWithAuthorities());
            }

            // Recalculate prices
            updateCartTotals(cart);

        }

        return save(cart);
    }

    @Override
    public TCart save(TCart tCart) {
        log.debug("Request to save tCart : {}", tCart);
        return tCartRepository.save(tCart);
    }

    @Override
    public TCart getCart(MUser user) throws RuntimeException {
        TCart cart = tCartRepository.findByMUser(user);

        if (cart != null) {
            return cart;
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    // Calculate the discount for a product
    private Double calculateDiscount(MProduct product) {
        return product.getDiscountPercentage() != null ? (product.getPrice() * product.getDiscountPercentage() / 100) : 0.0;
    }

    // Recalculate total price, discount, and final price
    private void updateCartTotals(TCart cart) {
        double totalPrice = 0.0;
        double totalDiscount = 0.0;

        for (TCartProduct item : cart.getCartItems()) {
            double itemTotal = item.getQty() * item.getPrice();
            double itemDiscount = item.getQty() * item.getSpecialPrice();

            totalPrice += itemTotal;
            totalDiscount += itemDiscount;
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscount(totalDiscount);
        cart.setFinalPrice(totalPrice - totalDiscount);
    }

}
