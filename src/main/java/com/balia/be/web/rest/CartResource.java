package com.balia.be.web.rest;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import com.balia.be.domain.TCart;
import com.balia.be.service.CartService;
import com.balia.be.service.MProductService;
import com.balia.be.web.rest.payload.request.CartListRequest;
import com.balia.be.web.rest.payload.request.CartRequest;
import com.balia.be.web.rest.payload.request.ProductRequest;
import com.balia.be.web.rest.payload.response.CartResponse;
import com.balia.be.web.rest.payload.response.MProductResponse;
import com.balia.be.web.rest.payload.response.MessageResponse;
import com.balia.be.web.rest.payload.response.ProductUpdateResponse;
import com.balia.be.web.rest.util.Constant;
import com.balia.be.web.rest.util.HeaderUtil;
import com.balia.be.web.rest.util.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
                cartResponse.setStatus(Constant.Status.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getLocalizedMessage()));
        }
        
        return ResponseEntity.created(new URI("/v1/api/master/cart/add" + cartResponse.getCartId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cartResponse.getCartId().toString()))
                .body(cartResponse);
    }

   
}