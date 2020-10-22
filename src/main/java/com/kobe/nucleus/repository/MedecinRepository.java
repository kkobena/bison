package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Medecin;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Medecin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long>, JpaSpecificationExecutor<Medecin> {
}
