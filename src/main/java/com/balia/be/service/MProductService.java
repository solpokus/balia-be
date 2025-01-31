package com.balia.be.service;

import com.balia.be.domain.MProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing product.
 */
@Service
public interface MProductService {

    /**
     * Save a mProduct.
     *
     * @param mProduct the entity to save
     * @return the persisted entity
     */
    MProduct save(MProduct mProduct);
    
    Page<MProduct> getAll(Pageable pageable);
    
    MProduct findOneById(Long mProductId);
    
    MProduct update(MProduct mProduct);
    
}
