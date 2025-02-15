package com.balia.be.web.rest;

import com.balia.be.domain.MCategories;
import com.balia.be.service.MCategoryService;
import com.balia.be.web.rest.payload.response.MessageResponse;
import com.balia.be.web.rest.payload.response.dto.MCategoryResponse;
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
 * REST controller for Category.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "Category Management", description = "Operations related to Categories")
public class CategoriesResource {

    private final Logger log = LoggerFactory.getLogger(CategoriesResource.class);

    private static final String ENTITY_NAME = "mCategories";
    
    @Autowired
    MCategoryService mCategoryService;

    /**
     * GET  /m-categories : get all the MCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MCategories in body
     */
    @GetMapping("/m-categories")
    public ResponseEntity<List<MCategoryResponse>> getAllMCategories(Pageable pageable) {
        log.info("REST request to get a page of MCategories {}",pageable);
        Page<MCategoryResponse> page = mCategoryService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /m-categories : Create a new mCategories.
     *
     * @param mCategories the mCategories to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mCategories, or with status 400 (Bad Request) if the mCategories has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/m-categories")
    public ResponseEntity<?> createCategory(@RequestBody MCategories mCategories) throws URISyntaxException {
        log.info("REST request to create MCategories : {}", mCategories);
        if (mCategories.getId() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("A new mCategories cannot already have an ID"));
        }
        MCategories result = mCategoryService.save(mCategories);
        return ResponseEntity.created(new URI("/v1/api/master/m-categories" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /m-categories : Updates an existing mCategories.
     *
     * @param mCategories the mCategories to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mCategories,
     * or with status 400 (Bad Request) if the mCategories is not valid,
     * or with status 500 (Internal Server Error) if the mCategories couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/m-categories")
    public ResponseEntity<?> updateCategories(@RequestBody MCategories mCategories) throws URISyntaxException {
        log.info("REST request to update MCategories : {}", mCategories);
        if (mCategoryService.findOneById(mCategories.getId()) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Category ID not exists"));
        }

        MCategories result = mCategoryService.update(mCategories);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mCategories.getId().toString()))
                .body(result);
    }

    /**
     * GET  /m-categories/all : get all the MCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of MCategoryResponse in body
     */
    @GetMapping("/m-categories/all")
    public ResponseEntity<List<MCategoryResponse>> getAllMCategoriesWithChild() {
        log.info("REST request to get all of MCategories");
        List<MCategoryResponse> page = mCategoryService.getAllCategoriesWithChildren();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}