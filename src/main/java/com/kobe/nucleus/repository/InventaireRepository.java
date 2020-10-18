package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Inventaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Inventaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventaireRepository extends JpaRepository<Inventaire, Long> {
}
