package com.balia.be.web.rest;

import com.balia.be.domain.MRole;
import com.balia.be.repository.MRoleRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for Role.
 */
@RestController
@RequestMapping("/v1/api/master")
@Tag(name = "Role Management", description = "Operations related to role")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Autowired
    MRoleRepository mRoleRepository;

    /**
     * GET  /m-role : get all the MRole.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of MRole in body
     */
    @GetMapping("/m-role")
    public ResponseEntity<List<MRole>> getAllMRole(Pageable pageable) {
        log.debug("REST request to get a page of MRole");
        List<MRole> page = mRoleRepository.findAllByOrderByIdAsc();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}