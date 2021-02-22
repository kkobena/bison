package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.StockoutService;
import com.kobe.nucleus.domain.Stockout;
import com.kobe.nucleus.repository.StockoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Stockout}.
 */
@Service
@Transactional
public class StockoutServiceImpl implements StockoutService {

    private final Logger log = LoggerFactory.getLogger(StockoutServiceImpl.class);

    private final StockoutRepository stockoutRepository;

    public StockoutServiceImpl(StockoutRepository stockoutRepository) {
        this.stockoutRepository = stockoutRepository;
    }

    /**
     * Save a stockout.
     *
     * @param stockout the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Stockout save(Stockout stockout) {
        log.debug("Request to save Stockout : {}", stockout);
        return stockoutRepository.save(stockout);
    }

    /**
     * Get all the stockouts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Stockout> findAll() {
        log.debug("Request to get all Stockouts");
        return stockoutRepository.findAll();
    }


    /**
     * Get one stockout by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Stockout> findOne(Long id) {
        log.debug("Request to get Stockout : {}", id);
        return stockoutRepository.findById(id);
    }

    /**
     * Delete the stockout by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stockout : {}", id);

        stockoutRepository.deleteById(id);
    }
}
