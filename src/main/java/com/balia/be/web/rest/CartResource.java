package com.balia.be.web.rest;

import com.balia.be.domain.MUser;
import com.balia.be.domain.TCart;
import com.balia.be.service.CartService;
import com.balia.be.service.UserService;
import com.balia.be.web.rest.payload.request.CartListRequest;
import com.balia.be.web.rest.payload.response.CartResponse;
import com.balia.be.web.rest.payload.response.MessageResponse;
import com.balia.be.web.rest.util.Constant;
import com.balia.be.web.rest.util.HeaderUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for Cart.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "Cart Management", description = "Operations related to Cart")
public class CartResource {

    private final Logger log = LoggerFactory.getLogger(CartResource.class);

    private static final String ENTITY_NAME = "tCart";
    
    @Autowired
    CartService cartService;
    
    @Autowired
    UserService userService;

    /**
     * GET  /cart : get cart user.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of TCart in body
     */
    @GetMapping("/cart")
    public ResponseEntity<?> getCart() throws URISyntaxException {
        log.info("REST request to get Cart : ");

        MUser user = userService.getUserWithAuthorities();

        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid request"));
        }

        CartResponse cartResponse = new CartResponse();
        
        TCart result = cartService.getCart(user);
        
        if( result != null){
            cartResponse.setCartId(result.getId());
            cartResponse.setTotalPrice(result.getTotalPrice());
            cartResponse.setTotalDiscount(result.getTotalDiscount());
            cartResponse.setFinalPrice(result.getFinalPrice());
            cartResponse.setCurrency(result.getCurrency());
            cartResponse.setStatus(Constant.Status.OK);
            cartResponse.setMessage("Cart found");
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Cart not found"));
        }
        
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
    
    /**
     * POST  /t-cart : Create a new tCart.
     *
     * @param cartRequest the cartRequest to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tCart, or with status 400 (Bad Request) if the tCart has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody CartListRequest cartRequest) throws URISyntaxException {
        log.info("REST request to create Cart : {}", cartRequest);

        if (cartRequest == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid payload cart"));
        }

        TCart result;
        CartResponse cartResponse = new CartResponse();

        try {
            result = cartService.addToCart(cartRequest);
            if (result.getId() != null) {
                cartResponse.setCartId(result.getId());
                cartResponse.setTotalPrice(result.getTotalPrice());
                cartResponse.setTotalDiscount(result.getTotalDiscount());
                cartResponse.setFinalPrice(result.getFinalPrice());
                cartResponse.setCurrency(result.getCurrency());
                cartResponse.setStatus(Constant.Status.OK);
                cartResponse.setMessage("Cart successfully added");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getLocalizedMessage()));
        }
        
        return ResponseEntity.created(new URI("/v1/api/master/cart/add" + cartResponse.getCartId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cartResponse.getCartId().toString()))
                .body(cartResponse);
    }

   
}