package com.balia.be.web.rest;

import com.balia.be.domain.MProduct;
import com.balia.be.service.MProductService;
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
 * REST controller for Product.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "Product Management", description = "Operations related to Product")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "mProduct";
    
    @Autowired
    MProductService mProductService;

    /**
     * GET  /m-product : get all the MProduct.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MProduct in body
     */
    @GetMapping("/m-product")
    public ResponseEntity<List<MProduct>> getAllMProduct(Pageable pageable) {
        log.info("REST request to get a page of MProduct {}",pageable);
        Page<MProduct> page = mProductService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-product");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /m-product : Create a new mProduct.
     *
     * @param mProduct the mProduct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mProduct, or with status 400 (Bad Request) if the mProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/m-product")
    public ResponseEntity<?> createProduct(@RequestBody MProduct mProduct) throws URISyntaxException {
        log.info("REST request to create MProduct : {}", mProduct);
        if (mProduct.getId() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("A new mProduct cannot already have an ID"));
        }
        MProduct result = mProductService.save(mProduct);
        return ResponseEntity.created(new URI("/v1/api/master/m-product" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /m-product : Updates an existing mProduct.
     *
     * @param mProduct the mProduct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mProduct,
     * or with status 400 (Bad Request) if the mProduct is not valid,
     * or with status 500 (Internal Server Error) if the mProduct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/m-product")
    public ResponseEntity<?> updateProduct(@RequestBody MProduct mProduct) throws URISyntaxException {
        log.info("REST request to update MProduct : {}", mProduct);
        if (mProductService.findOneById(mProduct.getId()) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Product ID not exists"));
        }

        MProduct result = mProductService.update(mProduct);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mProduct.getId().toString()))
                .body(result);
    }
}