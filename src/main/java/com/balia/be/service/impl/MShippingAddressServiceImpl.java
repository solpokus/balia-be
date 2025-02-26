package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MShippingAddress;
import com.balia.be.repository.MShippingAddressRepository;
import com.balia.be.service.MShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MShippingAddressServiceImpl implements MShippingAddressService {

    private final Logger log = LoggerFactory.getLogger(MShippingAddressServiceImpl.class);
    
    @Autowired
    MShippingAddressRepository mShippingAddressRepository; 

    /**
     * Save a mShippingAddress.
     *
     * @param mShippingAddress
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public MShippingAddress save(MShippingAddress mShippingAddress) {
        log.debug("Request to save MShippingAddress : {}", mShippingAddress);
        
        String username = SecurityUtils.getCurrentUserLogin();
        mShippingAddress.setCreatedBy(username);
        mShippingAddress.setCreatedDate(new Date());

        return mShippingAddressRepository.save(mShippingAddress);
    }

    @Override
    public Page<MShippingAddress> getAll(Pageable pageable) {
        return mShippingAddressRepository.findAll(pageable);
    }

    @Override
    public MShippingAddress findOneById(Long mShippingAddressId) {
        return mShippingAddressRepository.findOneById(mShippingAddressId);
    }

    @Override
    public MShippingAddress update(MShippingAddress mShippingAddress) {
        log.debug("Request to update MShippingAddress : {}", mShippingAddress);
        String username = SecurityUtils.getCurrentUserLogin();

        MShippingAddress newData =  mShippingAddressRepository.findOneById(mShippingAddress.getId());

        newData.setmUser(mShippingAddress.getmUser());
        newData.setAddressLine1(mShippingAddress.getAddressLine1());
        newData.setAddressLine2(mShippingAddress.getAddressLine2());
        newData.setCity(mShippingAddress.getCity());
        newData.setState(mShippingAddress.getState());
        newData.setZipCode(mShippingAddress.getZipCode());
        newData.setCountry(mShippingAddress.getCountry());
        newData.setPrimary(mShippingAddress.isPrimary());
        newData.setLastModifiedBy(username);
        newData.setLastModifiedDate(new Date());
        mShippingAddressRepository.save(newData);
        
        return newData;
    }
}
