package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.DetailsAjustement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailsAjustement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailsAjustementRepository extends JpaRepository<DetailsAjustement, Long> {
}
