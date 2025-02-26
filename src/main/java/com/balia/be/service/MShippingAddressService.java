package com.balia.be.service;

import com.balia.be.domain.MShippingAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing shipping address.
 */
@Service
public interface MShippingAddressService {

    /**
     * Save a mShippingAddress.
     *
     * @param mShippingAddress the entity to save
     * @return the persisted entity
     */
    MShippingAddress save(MShippingAddress mShippingAddress);
    
    Page<MShippingAddress> getAll(Pageable pageable);

    MShippingAddress findOneById(Long id);

    MShippingAddress update(MShippingAddress mShippingAddress);
    
}
