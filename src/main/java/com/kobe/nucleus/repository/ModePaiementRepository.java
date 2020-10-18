package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.ModePaiement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModePaiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModePaiementRepository extends JpaRepository<ModePaiement, Long> {
}
