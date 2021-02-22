package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.TypeRisque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeRisque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeRisqueRepository extends JpaRepository<TypeRisque, Long> {
  
}
