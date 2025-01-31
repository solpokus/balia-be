package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MProduct;
import com.balia.be.repository.MProductRepository;
import com.balia.be.service.MProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MProductServiceImpl implements MProductService {

    private final Logger log = LoggerFactory.getLogger(MProductServiceImpl.class);
    
    @Autowired
    MProductRepository mProductRepository; 

    /**
     * Save a mProduct.
     *
     * @param mProduct
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public MProduct save(MProduct mProduct) {
        log.debug("Request to save MProduct : {}", mProduct);
        
        String username = SecurityUtils.getCurrentUserLogin();
        mProduct.setCreatedBy(username);
        mProduct.setCreatedDate(new Date());

        return mProductRepository.save(mProduct);
    }

    @Override
    public Page<MProduct> getAll(Pageable pageable) {
        return mProductRepository.findAll(pageable);
    }

    @Override
    public MProduct findOneById(Long mProductId) {
        return mProductRepository.findOneById(mProductId);
    }

    @Override
    public MProduct update(MProduct mProduct) {
        log.debug("Request to update MProduct : {}", mProduct);
        String username = SecurityUtils.getCurrentUserLogin();
        
        MProduct newData =  mProductRepository.findOneById(mProduct.getId());  
        newData.setLastModifiedBy(username);
        newData.setLastModifiedDate(new Date());
        newData.setName(mProduct.getName());
        newData.setSku(mProduct.getSku());
        newData.setPrice(mProduct.getPrice());
        newData.setCurrency(mProduct.getCurrency());
        newData.setStock(mProduct.getStock());
        newData.setStatus(mProduct.getStatus());
        newData.setShortDescription(mProduct.getShortDescription());
        newData.setLongDescription(mProduct.getLongDescription());
        newData.setmCategories(mProduct.getmCategories());
        mProductRepository.save(newData);
        
        return newData;
    }
}
