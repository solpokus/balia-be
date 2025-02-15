package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MCategories;
import com.balia.be.repository.MCategoriesRepository;
import com.balia.be.service.MCategoryService;
import com.balia.be.web.rest.payload.response.dto.MCategoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MCategoryServiceImpl implements MCategoryService {

    private final Logger log = LoggerFactory.getLogger(MCategoryServiceImpl.class);
    
    @Autowired
    MCategoriesRepository mCategoriesRepository; 

    /**
     * Save a mCategories.
     *
     * @param mCategories
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public MCategories save(MCategories mCategories) {
        log.debug("Request to save MCategories : {}", mCategories);
        
        String username = SecurityUtils.getCurrentUserLogin();
        mCategories.setCreatedBy(username);
        mCategories.setCreatedDate(new Date());

        return mCategoriesRepository.save(mCategories);
    }

    @Override
    public Page<MCategoryResponse> getAll(Pageable pageable) {
        return mCategoriesRepository.findAllNativeQuery(pageable);
    }

    @Override
    public MCategories findOneById(Long mCategoriesId) {
        return mCategoriesRepository.findOneById(mCategoriesId);
    }

    @Override
    public MCategories update(MCategories mCategories) {
        log.debug("Request to update MCategories : {}", mCategories);
        String username = SecurityUtils.getCurrentUserLogin();
        
        MCategories newData =  mCategoriesRepository.findOneById(mCategories.getId());  
        newData.setLastModifiedBy(username);
        newData.setLastModifiedDate(new Date());
        newData.setName(mCategories.getName());
        newData.setDescription(mCategories.getDescription());
        newData.setStatus(mCategories.getStatus());
        mCategoriesRepository.save(newData);
        
        return newData;
    }

    @Override
    public List<MCategoryResponse> getAllCategoriesWithChildren() {
        List<MCategories> categories = mCategoriesRepository.findAllWithChildren();
        return categories.stream().map(MCategoryResponse::fromEntity).collect(Collectors.toList());
    }
}
