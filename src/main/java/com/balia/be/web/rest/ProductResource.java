package com.balia.be.web.rest;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import com.balia.be.service.MProductService;
import com.balia.be.web.rest.payload.request.ProductRequest;
import com.balia.be.web.rest.payload.request.QueryProduct;
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
    public ResponseEntity<List<MProductResponse>> getAllMProduct(Pageable pageable) {
        log.info("REST request to get a page of MProduct {}",pageable);
//        Page<MProduct> page = mProductService.getAll(pageable);
        Page<MProductResponse> page = mProductService.getAllProductPage(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-product");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    /**
     * GET  /m-product/by-category : get all the MProduct.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MProduct in body
     */
    @GetMapping("/m-product/by-query")
    public ResponseEntity<List<MProductResponse>> getAllMProductByQuery(Pageable pageable, QueryProduct queryProduct) {
        log.info("REST request to get a page of MProduct query {}, {}",pageable, queryProduct);
        Page<MProductResponse> page = mProductService.getAllProductPageByQuery(pageable, queryProduct);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-product/by-query");
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

    /**
     * POST  /m-products : Create a new mProduct with images.
     *
     * @param metadataProduct the mProduct to create
     * @param files the files to upload
     * @return the ResponseEntity with status 201 (Created) and with body the new List<MProductImage>, or with status 400 (Bad Request) if the mProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/m-products", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> createProductAndImages(
            @RequestParam("metadata") String metadataProduct,
            @RequestParam("files") MultipartFile[] files) throws URISyntaxException {
        
        log.info("REST request to create MProduct with files : {}, {}", metadataProduct, files);

        if(metadataProduct.isEmpty()){
            return ResponseEntity.badRequest().body(new MessageResponse("Please check metadata"));
        }

        MProduct mProduct;
        
        try{
            // Convert JSON metadata string to Java Object
            ObjectMapper objectMapper = new ObjectMapper();
            mProduct = objectMapper.readValue(metadataProduct, MProduct.class);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Metadata is not valid"));
        }
        
        if (mProduct.getId() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("A new mProduct cannot already have an ID"));
        }
        
        List<MProductImage> result = mProductService.saveWithImage(mProduct, files);
        return ResponseEntity.created(new URI("/v1/api/master/m-products"))
                .headers(HeaderUtil.createEntityCreationAlert("mProductImage", result.toString()))
                .body(result);
    }

    /**
     * PUT  /m-products : Create a new mProduct with images.
     *
     * @param metadataProduct the mProduct to update
     * @param files the files to upload
     * @return the ResponseEntity with status 201 (Created) and with body the new List<MProductImage>, or with status 400 (Bad Request) if the mProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping(value = "/m-products", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateProductAndImages(
            @RequestParam("metadata") String metadataProduct,
            @RequestParam("files") MultipartFile[] files) throws URISyntaxException {

        log.info("REST request to update MProduct with files : {}, {}", metadataProduct, files);

        if (metadataProduct.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Please check metadata, metadata is not valid"));
        }

        ProductRequest productRequest;
        ProductUpdateResponse response;

        try {
            // Convert JSON metadata string to Java Object
            ObjectMapper objectMapper = new ObjectMapper();
            productRequest = objectMapper.readValue(metadataProduct, ProductRequest.class);

            response = mProductService.updateWithImage(productRequest, files);

            if (!(Constant.Status.OK).equals(response.getStatus())) {
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            log.error("updateProductAndImages - Exception : {}", e.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(new MessageResponse(Constant.Status.ERROR));
        }

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, response.toString()))
                .body(response);

    }
}