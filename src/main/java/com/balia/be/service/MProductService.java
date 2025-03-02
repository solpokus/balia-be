package com.balia.be.service;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import com.balia.be.web.rest.payload.request.ProductRequest;
import com.balia.be.web.rest.payload.request.QueryProduct;
import com.balia.be.web.rest.payload.response.MProductResponse;
import com.balia.be.web.rest.payload.response.ProductUpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    
    List<MProductImage> saveWithImage(MProduct mProduct, MultipartFile[] files);

    ProductUpdateResponse updateWithImage(ProductRequest mProduct, MultipartFile[] files);
    
    Page<MProduct> getAll(Pageable pageable);
    
    MProduct findOneById(Long mProductId);
    
    MProduct update(MProduct mProduct);
    
    Page<MProductResponse> getAllProductPage(Pageable pageable);

    Page<MProductResponse> getAllProductPageByQuery(Pageable pageable, QueryProduct queryProduct);
    
}
