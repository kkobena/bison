package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.FournisseurProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FournisseurProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FournisseurProduitRepository extends JpaRepository<FournisseurProduit, Long> {
}
