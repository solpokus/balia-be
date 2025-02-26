package com.balia.be.repository;

import com.balia.be.domain.MShippingAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MShippingAddressRepository extends JpaRepository<MShippingAddress, Long> {

    Page<MShippingAddress> findAll(Pageable pageable);

    MShippingAddress findOneById(Long id);
}
