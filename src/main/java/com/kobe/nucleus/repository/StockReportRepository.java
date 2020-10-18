package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.StockReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StockReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockReportRepository extends JpaRepository<StockReport, Long> {
}
