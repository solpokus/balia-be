package com.balia.be.web.rest;

import com.balia.be.domain.MAboutUs;
import com.balia.be.service.MAboutUsService;
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
 * REST controller for AboutUs.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "AboutUs Management", description = "Operations related to AboutUs")
public class AboutUsResource {

    private final Logger log = LoggerFactory.getLogger(AboutUsResource.class);

    private static final String ENTITY_NAME = "mAboutUs";
    
    @Autowired
    MAboutUsService mAboutUsService;

    /**
     * GET  /m-about-us : get all the MAboutUs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MAboutUs in body
     */
    @GetMapping("/m-about-us")
    public ResponseEntity<List<MAboutUs>> getAllMAboutUs(Pageable pageable) {
        log.info("REST request to get a page of MAboutUs {}",pageable);
        Page<MAboutUs> page = mAboutUsService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/v1/api/master/m-about-us");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /m-AboutUs : Create a new mAboutUs.
     *
     * @param mAboutUs the mAboutUs to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mAboutUs, or with status 400 (Bad Request) if the mAboutUs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/m-about-us")
    public ResponseEntity<?> createAboutUs(@RequestBody MAboutUs mAboutUs) throws URISyntaxException {
        log.debug("REST request to save MAboutUs : {}", mAboutUs);
        if (mAboutUs.getId() != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("A new mAboutUs cannot already have an ID"));
        }
        MAboutUs result = mAboutUsService.save(mAboutUs);
        return ResponseEntity.created(new URI("/v1/api/master/m-about-us" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /m-AboutUs : Updates an existing mAboutUs.
     *
     * @param mAboutUs the mAboutUs to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mAboutUs,
     * or with status 400 (Bad Request) if the mAboutUs is not valid,
     * or with status 500 (Internal Server Error) if the mAboutUs couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/m-about-us")
    public ResponseEntity<?> updateAboutUs(@RequestBody MAboutUs mAboutUs) throws URISyntaxException {
        log.debug("REST request to update MAboutUs : {}", mAboutUs);
        if (mAboutUsService.findOneById(mAboutUs.getId()) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("AboutUs ID not exists"));
        }
        
        log.info(" data : {}", mAboutUs);

        MAboutUs result = mAboutUsService.update(mAboutUs);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mAboutUs.getId().toString()))
                .body(result);
    }
}