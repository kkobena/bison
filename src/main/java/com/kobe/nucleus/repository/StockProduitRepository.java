package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.StockProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StockProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockProduitRepository extends JpaRepository<StockProduit, Long> {
}
