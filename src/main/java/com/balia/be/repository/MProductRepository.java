package com.balia.be.repository;

import com.balia.be.domain.MProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MProductRepository extends JpaRepository<MProduct, Long> {

    Optional<MProduct> findByName(String name);

    Page<MProduct> findAll(Pageable pageable);

    MProduct findOneById(Long id);
}
