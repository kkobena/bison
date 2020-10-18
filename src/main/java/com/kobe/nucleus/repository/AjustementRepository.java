package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Ajustement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ajustement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AjustementRepository extends JpaRepository<Ajustement, Long> {
}
