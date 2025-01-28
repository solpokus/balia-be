package com.balia.be.service;

import com.balia.be.domain.MCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    
    Page<MCategories> getAll(Pageable pageable);
    
    MCategories findOneById(Long mCategoriesId);
    
    MCategories update(MCategories mCategories);
    
}
