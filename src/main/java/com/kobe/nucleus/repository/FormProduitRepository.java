package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.FormProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FormProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormProduitRepository extends JpaRepository<FormProduit, Long> {
}
