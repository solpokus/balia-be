package com.balia.be.web.rest;

import com.balia.be.domain.MShippingAddress;
import com.balia.be.service.MShippingAddressService;
import com.balia.be.web.rest.payload.response.MessageResponse;
import com.balia.be.web.rest.util.HeaderUtil;
import com.balia.be.web.rest.util.PaginationUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for ShippingAddress.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "ShippingAddress Management", description = "Operations related to ShippingAddress")
public class ShippingAddressResource {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressResource.class);

    private static final String ENTITY_NAME = "mShippingAddress";
    
    @Autowired
    MShippingAddressService mShippingAddressService;


    /**
     * GET  /m-shipping-address : get all the MShippingAddress.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MShippingAddress in body
     */
    @GetMapping("/user/shipping-address")
    public ResponseEntity<List<MShippingAddress>> getAllMShippingAddress(Pageable pageable) {
        log.info("REST request to get a page of MShippingAddress {}",pageable);
        Page<MShippingAddress> page = mShippingAddressService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/user/shipping-address");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /m-ShippingAddress : Create a new mShippingAddress.
     *
     * @param mShippingAddress the mShippingAddress to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mShippingAddress, or with status 400 (Bad Request) if the mShippingAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user/shipping-address")
    public ResponseEntity<?> createShippingAddress(@RequestBody MShippingAddress mShippingAddress) throws URISyntaxException {
        log.info("REST request to create MShippingAddress : {}", mShippingAddress);
        if (mShippingAddress.getId() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("A new mShippingAddress cannot already have an ID"));
        }
        MShippingAddress result = mShippingAddressService.save(mShippingAddress);
        return ResponseEntity.created(new URI("/v1/api/master/user/shipping-address" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /m-ShippingAddress : Updates an existing mShippingAddress.
     *
     * @param mShippingAddress the mShippingAddress to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mShippingAddress,
     * or with status 400 (Bad Request) if the mShippingAddress is not valid,
     * or with status 500 (Internal Server Error) if the mShippingAddress couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user/shipping-address")
    public ResponseEntity<?> updateShippingAddress(@RequestBody MShippingAddress mShippingAddress) throws URISyntaxException {
        log.info("REST request to update MShippingAddress : {}", mShippingAddress);
        if (mShippingAddressService.findOneById(mShippingAddress.getId()) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("ShippingAddress ID not exists"));
        }

        MShippingAddress result = mShippingAddressService.update(mShippingAddress);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mShippingAddress.getId().toString()))
                .body(result);
    }
}