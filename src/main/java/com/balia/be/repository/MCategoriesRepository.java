package com.balia.be.repository;

import com.balia.be.domain.MCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MCategoriesRepository extends JpaRepository<MCategories, Long> {

    Optional<MCategories> findByName(String name);

    Page<MCategories> findAll(Pageable pageable);
    
    MCategories findOneById(Long id);
}
