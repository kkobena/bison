package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.TypeInventaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeInventaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeInventaireRepository extends JpaRepository<TypeInventaire, Long> {
}
