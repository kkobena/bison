package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.RetourFournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RetourFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RetourFournisseurRepository extends JpaRepository<RetourFournisseur, Long> {
}
