package com.balia.be.repository;

import com.balia.be.domain.MProduct;
import com.balia.be.web.rest.payload.response.MProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MProductRepository extends JpaRepository<MProduct, Long> {

    Optional<MProduct> findByName(String name);

    Page<MProduct> findAll(Pageable pageable);

    MProduct findOneById(Long id);

    @Query(value = "SELECT new com.balia.be.web.rest.payload.response.MProductResponse( mp.id , mp.name, " 
            + " mp.sku, mp.price, mp.currency, mp.stock, mp.status, "
            + " mp.shortDescription, mp.longDescription , mc, "
            + " mp.createdBy , mp.createdDate , mp.lastModifiedBy , "
            + " mp.lastModifiedDate ) "
            + " FROM MProduct mp "
            + " LEFT JOIN MCategories mc on mp.mCategories.id = mc.id ")
//            + "WHERE (?1 IS NULL OR tr.mStatus.Id IN ?1) ")
    Page<MProductResponse> findAllProducts(Pageable pageable);
}
