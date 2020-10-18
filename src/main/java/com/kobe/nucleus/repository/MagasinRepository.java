package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Magasin;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Magasin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {
}
