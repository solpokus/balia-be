package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import com.balia.be.repository.MProductImageRepository;
import com.balia.be.repository.MProductRepository;
import com.balia.be.service.MProductService;
import com.balia.be.service.util.RandomUtil;
import com.balia.be.web.rest.payload.request.ProductRequest;
import com.balia.be.web.rest.payload.request.QueryProduct;
import com.balia.be.web.rest.payload.response.MProductResponse;
import com.balia.be.web.rest.payload.response.ProductUpdateResponse;
import com.balia.be.web.rest.payload.response.dto.MProductImageDTO;
import com.balia.be.web.rest.util.Constant;
import jakarta.persistence.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MProductServiceImpl implements MProductService {

    private final Logger log = LoggerFactory.getLogger(MProductServiceImpl.class);

    @Value("${application.upload.dir}")
    private String uploadDir;

    @Value("${application.cdn.url}")
    private String appCdnUrl;
    
    @Autowired
    MProductRepository mProductRepository; 
    
    @Autowired
    MProductImageRepository mProductImageRepository;

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
    @Transactional
    public List<MProductImage> saveWithImage(MProduct mProduct, MultipartFile[] files) {
        log.debug("Request to save MProduct with images : {}, {}", mProduct, files);
        List<MProductImage> data = new ArrayList<>();
        
        
        String username = SecurityUtils.getCurrentUserLogin();
        mProduct.setCreatedBy(username);
        mProduct.setCreatedDate(new Date());
        
        save(mProduct);

        for (MultipartFile file : files) {
            try {
                MProductImage mProductImage = new MProductImage();
                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
                Files.write(filePath, file.getBytes());

//                fileUrls.add(appCdnUrl + file.getOriginalFilename());
                mProductImage.setCreatedBy(username);
                mProductImage.setCreatedDate(new Date());
                mProductImage.setImage(appCdnUrl + RandomUtil.genText() + "-" + file.getOriginalFilename());
                mProductImage.setOriginalName(file.getOriginalFilename());
                mProductImage.setmProduct(mProduct);

                mProductImageRepository.save(mProductImage);
                data.add(mProductImage);

            } catch (IOException e) {
                log.error("saveWithImage - File upload failed for {}", file.getOriginalFilename());
            }
        }

        return data;
    }

    @Override
    @Transactional
    public ProductUpdateResponse updateWithImage(ProductRequest productRequest, MultipartFile[] files) {
        log.debug("Request to update MProduct with images : {}, {}", productRequest, files);

        ProductUpdateResponse response = new ProductUpdateResponse();

        MProduct newData = findOneById(productRequest.getId());
        
        if(newData != null){
            String username = SecurityUtils.getCurrentUserLogin();
            newData.setLastModifiedBy(username);
            newData.setLastModifiedDate(new Date());
            newData.setName(productRequest.getName() == null ? newData.getName() : productRequest.getName());
            newData.setSku(productRequest.getSku() == null ? newData.getSku() : productRequest.getSku());
            newData.setPrice(productRequest.getPrice() == null ? newData.getPrice() : productRequest.getPrice());
            newData.setCurrency(productRequest.getCurrency() == null ? newData.getCurrency() : productRequest.getCurrency());
            newData.setStock(productRequest.getStock() == null ? newData.getStock() : productRequest.getStock());
            newData.setStatus(productRequest.getStatus() == null ? newData.getStatus() : productRequest.getStatus());
            newData.setShortDescription(productRequest.getShortDescription() == null ? newData.getShortDescription() : productRequest.getShortDescription());
            newData.setLongDescription(productRequest.getLongDescription() == null ? newData.getLongDescription() : productRequest.getLongDescription());
            newData.setmCategories(productRequest.getmCategories() == null ? newData.getmCategories() : productRequest.getmCategories());
            newData.setSustainabilityFeature(productRequest.getSustainabilityFeature() == null ? newData.getSustainabilityFeature() : productRequest.getSustainabilityFeature());
            newData.setMaterial(productRequest.getMaterial() == null ? newData.getMaterial() : productRequest.getMaterial());
            newData.setDiscountPercentage(productRequest.getDiscountPercentage() == null ? newData.getDiscountPercentage() : productRequest.getDiscountPercentage());
            newData.setPreOrder(productRequest.getPreOrder() == null ? newData.getPreOrder() : productRequest.getPreOrder());
            
            if(files != null){
                for (MultipartFile file : files) {
                    
                    // Check Image existing
                    List<MProductImage> mProductImages = mProductImageRepository.findAllByMProductIdAndOriginalName(productRequest.getId(),file.getOriginalFilename());
                    if(mProductImages.isEmpty()) {
                        try {
                            MProductImage mProductImage = new MProductImage();
                            File uploadPath = new File(uploadDir);
                            if (!uploadPath.exists()) {
                                uploadPath.mkdirs();
                            }

                            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
                            Files.write(filePath, file.getBytes());

                            mProductImage.setCreatedBy(username);
                            mProductImage.setCreatedDate(new Date());
                            mProductImage.setImage(appCdnUrl + RandomUtil.genText() + "-" + file.getOriginalFilename());
                            mProductImage.setOriginalName(file.getOriginalFilename());
                            mProductImage.setmProduct(newData);

                            mProductImageRepository.save(mProductImage);

                        } catch (IOException e) {
                            log.error("updateWithImage - File upload failed for {}", file.getOriginalFilename());
                            response.setStatus(Constant.Status.NOT_OK);
                        }
                    }
                }
            }
            
            mProductRepository.save(newData);
            
            response.setStatus(Constant.Status.OK);
            response.setmProduct(newData);
        } else {
            response.setStatus(Constant.Status.NOT_OK);
            response.setMessage("Product not found");
        }

        return response;
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
        newData.setSustainabilityFeature(mProduct.getSustainabilityFeature());
        newData.setMaterial(mProduct.getMaterial());
        mProductRepository.save(newData);
        
        return newData;
    }

    @Override
    public Page<MProductResponse> getAllProductPage(Pageable pageable) {
        Page<MProductResponse> mProductResponsePage;

        mProductResponsePage = mProductRepository.findAllProducts(pageable);

        if (!mProductResponsePage.isEmpty()) {
            for (MProductResponse data : mProductResponsePage) {
                if (data.getId() != null) {
                    List<MProductImage> mProductImages = mProductImageRepository.findAllByMProductId(data.getId());
                    if (!mProductImages.isEmpty()) {
                        List<MProductImageDTO> mProductImageDTOS = mProductImages.stream()
                                .map(m -> new MProductImageDTO(
                                        m.getId(), m.getOriginalName(), m.getImage(), m.getCreatedBy(), 
                                        m.getCreatedDate(), m.getLastModifiedBy(), m.getLastModifiedDate()
                                )).collect(Collectors.toList());
                        data.setmProductImages(mProductImageDTOS);
                    }
                }
            }
        }

        return mProductResponsePage;
    }

    @Override
    public Page<MProductResponse> getAllProductPageByQuery(Pageable pageable, QueryProduct queryProduct) {
        Page<MProductResponse> mProductResponsePage;

        mProductResponsePage = mProductRepository.findAllProductsByCategoryId(pageable, queryProduct.getCategoryId());

        if (!mProductResponsePage.isEmpty()) {
            for (MProductResponse data : mProductResponsePage) {
                if (data.getId() != null) {
                    List<MProductImage> mProductImages = mProductImageRepository.findAllByMProductId(data.getId());
                    if (!mProductImages.isEmpty()) {
                        List<MProductImageDTO> mProductImageDTOS = mProductImages.stream()
                                .map(m -> new MProductImageDTO(
                                        m.getId(), m.getOriginalName(), m.getImage(), m.getCreatedBy(),
                                        m.getCreatedDate(), m.getLastModifiedBy(), m.getLastModifiedDate()
                                )).collect(Collectors.toList());
                        data.setmProductImages(mProductImageDTOS);
                    }
                }
            }
        }

        return mProductResponsePage;
    }

}
