package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.CodeGestion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CodeGestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodeGestionRepository extends JpaRepository<CodeGestion, Long> {
}
