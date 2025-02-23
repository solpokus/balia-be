package com.balia.be.repository;

import com.balia.be.domain.MProduct;
import com.balia.be.domain.MProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MProductImageRepository extends JpaRepository<MProductImage, Long> {

    Page<MProductImage> findAll(Pageable pageable);

    MProductImage findOneById(Long id);
    
    List<MProductImage> findAllByMProductId(Long mProductId);
    
    List<MProductImage> findAllByMProductIdAndOriginalName(Long mProductId, String imageOriginalName);
}
