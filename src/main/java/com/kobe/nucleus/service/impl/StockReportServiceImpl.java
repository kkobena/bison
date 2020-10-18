package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.StockReportService;
import com.kobe.nucleus.domain.StockReport;
import com.kobe.nucleus.repository.StockReportRepository;
import com.kobe.nucleus.service.dto.StockReportDTO;
import com.kobe.nucleus.service.mapper.StockReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StockReport}.
 */
@Service
@Transactional
public class StockReportServiceImpl implements StockReportService {

    private final Logger log = LoggerFactory.getLogger(StockReportServiceImpl.class);

    private final StockReportRepository stockReportRepository;

    private final StockReportMapper stockReportMapper;

    public StockReportServiceImpl(StockReportRepository stockReportRepository, StockReportMapper stockReportMapper) {
        this.stockReportRepository = stockReportRepository;
        this.stockReportMapper = stockReportMapper;
    }

    /**
     * Save a stockReport.
     *
     * @param stockReportDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StockReportDTO save(StockReportDTO stockReportDTO) {
        log.debug("Request to save StockReport : {}", stockReportDTO);
        StockReport stockReport = stockReportMapper.toEntity(stockReportDTO);
        stockReport = stockReportRepository.save(stockReport);
        return stockReportMapper.toDto(stockReport);
    }

    /**
     * Get all the stockReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockReports");
        return stockReportRepository.findAll(pageable)
            .map(stockReportMapper::toDto);
    }


    /**
     * Get one stockReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockReportDTO> findOne(Long id) {
        log.debug("Request to get StockReport : {}", id);
        return stockReportRepository.findById(id)
            .map(stockReportMapper::toDto);
    }

    /**
     * Delete the stockReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockReport : {}", id);

        stockReportRepository.deleteById(id);
    }
}
