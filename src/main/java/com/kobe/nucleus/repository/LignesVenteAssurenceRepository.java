package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.LignesVenteAssurence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LignesVenteAssurence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LignesVenteAssurenceRepository extends JpaRepository<LignesVenteAssurence, Long> {
}
