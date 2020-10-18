package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Fabriquant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fabriquant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabriquantRepository extends JpaRepository<Fabriquant, Long> {
}
