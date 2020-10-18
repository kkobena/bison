package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.LignesVente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LignesVente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LignesVenteRepository extends JpaRepository<LignesVente, Long> {
}
