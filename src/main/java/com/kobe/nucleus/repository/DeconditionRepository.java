package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Decondition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Decondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeconditionRepository extends JpaRepository<Decondition, Long> {
}
