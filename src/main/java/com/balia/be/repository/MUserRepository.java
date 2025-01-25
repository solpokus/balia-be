package com.balia.be.repository;

import com.balia.be.domain.MUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MUserRepository extends JpaRepository<MUser, Long> {

    Optional<MUser> findByLogin(String username);

    Boolean existsByLogin(String username);

    @Query("SELECT u FROM MUser u WHERE u.activationKey = ?1")
    public MUser findByActivationKey(String code);

    Optional<MUser> findOneWithAuthoritiesByLogin(String username);
    
    MUser findOneById(Long userId);
    
    Page<MUser> findAllByActivated(Pageable pageable, Boolean isActive);
}
