package com.balia.be.repository;

import com.balia.be.domain.MCategories;
import com.balia.be.web.rest.payload.response.dto.MCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MCategoriesRepository extends JpaRepository<MCategories, Long> {

    Optional<MCategories> findByName(String name);

    Page<MCategories> findAll(Pageable pageable);
    
    MCategories findOneById(Long id);

    @Query("SELECT c FROM MCategories c LEFT JOIN FETCH c.children WHERE c.parent IS NULL")
    List<MCategories> findAllWithChildren();

    @Query(value = "SELECT new com.balia.be.web.rest.payload.response.dto.MCategoryResponse( mc.id as id, " 
            + "mc.name as name, mc.status as status, mc.parent.Id as parentId) "
            + "FROM MCategories mc ")
    Page<MCategoryResponse> findAllNativeQuery(Pageable pageable);
}
