package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Motif;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Motif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotifRepository extends JpaRepository<Motif, Long> {
}
