package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.MvtProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MvtProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MvtProduitRepository extends JpaRepository<MvtProduit, Long> {
}
