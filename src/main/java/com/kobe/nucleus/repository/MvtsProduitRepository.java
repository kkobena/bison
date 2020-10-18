package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.MvtsProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MvtsProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MvtsProduitRepository extends JpaRepository<MvtsProduit, Long> {
}
