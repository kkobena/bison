package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.CategorieAyantDroit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategorieAyantDroit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieAyantDroitRepository extends JpaRepository<CategorieAyantDroit, Long> {
}
