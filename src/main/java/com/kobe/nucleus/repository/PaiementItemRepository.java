package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.PaiementItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PaiementItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaiementItemRepository extends JpaRepository<PaiementItem, Long> {
}
