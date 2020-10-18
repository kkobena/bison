package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Compagnie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Compagnie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompagnieRepository extends JpaRepository<Compagnie, Long> {
}
