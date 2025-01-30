package com.balia.be.service;

import com.balia.be.domain.MAboutUs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing about-us.
 */
@Service
public interface MAboutUsService {

    /**
     * Save a mAboutUs.
     *
     * @param mAboutUs the entity to save
     * @return the persisted entity
     */
    MAboutUs save(MAboutUs mAboutUs);
    
    Page<MAboutUs> getAll(Pageable pageable);

    MAboutUs findOneById(Long mAboutUsId);

    MAboutUs update(MAboutUs mAboutUs);
    
}
