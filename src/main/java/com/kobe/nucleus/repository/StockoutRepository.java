package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Stockout;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Stockout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockoutRepository extends JpaRepository<Stockout, Long>, JpaSpecificationExecutor<Stockout> {
}
