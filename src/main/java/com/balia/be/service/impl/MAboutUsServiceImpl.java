package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MAboutUs;
import com.balia.be.repository.MAboutUsRepository;
import com.balia.be.service.MAboutUsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MAboutUsServiceImpl implements MAboutUsService {

    private final Logger log = LoggerFactory.getLogger(MAboutUsServiceImpl.class);
    
    @Autowired
    MAboutUsRepository mAboutUsRepository; 

    /**
     * Save a mAboutUs.
     *
     * @param mAboutUs
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public MAboutUs save(MAboutUs mAboutUs) {
        log.debug("Request to save MAboutUs : {}", mAboutUs);
        
        String username = SecurityUtils.getCurrentUserLogin();
        mAboutUs.setCreatedBy(username);
        mAboutUs.setCreatedDate(new Date());

        return mAboutUsRepository.save(mAboutUs);
    }

    @Override
    public Page<MAboutUs> getAll(Pageable pageable) {
        return mAboutUsRepository.findAll(pageable);
    }

    @Override
    public MAboutUs findOneById(Long mAboutUsId) {
        return mAboutUsRepository.findOneById(mAboutUsId);
    }

    @Override
    public MAboutUs update(MAboutUs mAboutUs) {
        log.debug("Request to update MAboutUs : {}", mAboutUs);
        String username = SecurityUtils.getCurrentUserLogin();
        
        MAboutUs newData =  mAboutUsRepository.findOneById(mAboutUs.getId());  
        newData.setLastModifiedBy(username);
        newData.setLastModifiedDate(new Date());
        newData.setName(mAboutUs.getName());
        newData.setShortDescription(mAboutUs.getShortDescription());
        mAboutUsRepository.save(newData);
        
        return newData;
    }
}
