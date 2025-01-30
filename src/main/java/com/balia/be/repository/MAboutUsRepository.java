package com.balia.be.repository;

import com.balia.be.domain.MAboutUs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MAboutUsRepository extends JpaRepository<MAboutUs, Long> {

    Optional<MAboutUs> findByName(String name);

    Page<MAboutUs> findAll(Pageable pageable);

    MAboutUs findOneById(Long id);
}
