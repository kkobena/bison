package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Etiquette;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Etiquette entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtiquetteRepository extends JpaRepository<Etiquette, Long> {
}
