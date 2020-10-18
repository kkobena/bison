package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.DetailsInventaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailsInventaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailsInventaireRepository extends JpaRepository<DetailsInventaire, Long> {
}
