package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.ModelFacture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModelFacture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModelFactureRepository extends JpaRepository<ModelFacture, Long> {
}
