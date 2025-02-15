package com.balia.be.service;

import com.balia.be.domain.MCategories;
import com.balia.be.web.rest.payload.response.dto.MCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing categories.
 */
@Service
public interface MCategoryService {

    /**
     * Save a mCategories.
     *
     * @param mCategories the entity to save
     * @return the persisted entity
     */
    MCategories save(MCategories mCategories);
    
    Page<MCategoryResponse> getAll(Pageable pageable);
    
    MCategories findOneById(Long mCategoriesId);
    
    MCategories update(MCategories mCategories);

    public List<MCategoryResponse> getAllCategoriesWithChildren();
    
}
