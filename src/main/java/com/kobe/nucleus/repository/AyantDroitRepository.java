package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.AyantDroit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AyantDroit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AyantDroitRepository extends JpaRepository<AyantDroit, Long> {
}
