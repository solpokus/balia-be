package com.balia.be.repository;

import com.balia.be.domain.MRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MRoleRepository extends JpaRepository<MRole, Long> {

    Optional<MRole> findByName(String name);

    List<MRole> findAllByOrderByIdAsc();
}
