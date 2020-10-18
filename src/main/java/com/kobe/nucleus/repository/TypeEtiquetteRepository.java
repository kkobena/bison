package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.TypeEtiquette;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeEtiquette entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEtiquetteRepository extends JpaRepository<TypeEtiquette, Long> {
}
