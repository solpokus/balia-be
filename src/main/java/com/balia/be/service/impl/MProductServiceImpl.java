package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import com.balia.be.repository.MProductImageRepository;
import com.balia.be.repository.MProductRepository;
import com.balia.be.service.MProductService;
import com.balia.be.service.util.RandomUtil;
import com.balia.be.web.rest.payload.response.MProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                log.error("File upload failed for {}", file.getOriginalFilename());
            }
        }

        return data;
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

    @Override
    public Page<MProductResponse> getAllProductPage(Pageable pageable) {
        Page<MProductResponse> mProductResponsePage;

        mProductResponsePage = mProductRepository.findAllProducts(pageable);

        if (!mProductResponsePage.isEmpty()) {
            for (MProductResponse data : mProductResponsePage) {
                if (data.getId() != null) {
                    List<MProductImage> mProductImages = mProductImageRepository.findAllByMProductId(data.getId());
                    data.setmProductImages(mProductImages);
                }
            }
        }

        return mProductResponsePage;
    }

}
