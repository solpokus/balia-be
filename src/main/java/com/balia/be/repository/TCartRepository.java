package com.balia.be.repository;

import com.balia.be.domain.MUser;
import com.balia.be.domain.TCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCartRepository extends JpaRepository<TCart, Long> {

    Page<TCart> findAll(Pageable pageable);
    
    TCart findByMUser(MUser mUser);
}
