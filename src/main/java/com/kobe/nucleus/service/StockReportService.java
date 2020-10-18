package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.StockReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.StockReport}.
 */
public interface StockReportService {

    /**
     * Save a stockReport.
     *
     * @param stockReportDTO the entity to save.
     * @return the persisted entity.
     */
    StockReportDTO save(StockReportDTO stockReportDTO);

    /**
     * Get all the stockReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockReportDTO> findOne(Long id);

    /**
     * Delete the "id" stockReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
