package com.balia.be.web.rest;

import com.balia.be.domain.MCategories;
import com.balia.be.repository.MCategoriesRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for Category.
 */
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "Category Management", description = "Operations related to Categories")
public class CategoriesResource {

    private final Logger log = LoggerFactory.getLogger(CategoriesResource.class);

    @Autowired
    MCategoriesRepository mCategoriesRepository;

    /**
     * GET  /m-categories : get all the MCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MCategories in body
     */
    @GetMapping("/m-categories")
    public ResponseEntity<List<MCategories>> getAllMCategories(Pageable pageable) {
        log.info("REST request to get a page of MCategories {}",pageable);
        Page<MCategories> page = mCategoriesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}