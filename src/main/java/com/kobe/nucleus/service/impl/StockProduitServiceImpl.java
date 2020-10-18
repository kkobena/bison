package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.StockProduitService;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.repository.StockProduitRepository;
import com.kobe.nucleus.service.dto.StockProduitDTO;
import com.kobe.nucleus.service.mapper.StockProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StockProduit}.
 */
@Service
@Transactional
public class StockProduitServiceImpl implements StockProduitService {

    private final Logger log = LoggerFactory.getLogger(StockProduitServiceImpl.class);

    private final StockProduitRepository stockProduitRepository;

    private final StockProduitMapper stockProduitMapper;

    public StockProduitServiceImpl(StockProduitRepository stockProduitRepository, StockProduitMapper stockProduitMapper) {
        this.stockProduitRepository = stockProduitRepository;
        this.stockProduitMapper = stockProduitMapper;
    }

    /**
     * Save a stockProduit.
     *
     * @param stockProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StockProduitDTO save(StockProduitDTO stockProduitDTO) {
        log.debug("Request to save StockProduit : {}", stockProduitDTO);
        StockProduit stockProduit = stockProduitMapper.toEntity(stockProduitDTO);
        stockProduit = stockProduitRepository.save(stockProduit);
        return stockProduitMapper.toDto(stockProduit);
    }

    /**
     * Get all the stockProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockProduits");
        return stockProduitRepository.findAll(pageable)
            .map(stockProduitMapper::toDto);
    }


    /**
     * Get one stockProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockProduitDTO> findOne(Long id) {
        log.debug("Request to get StockProduit : {}", id);
        return stockProduitRepository.findById(id)
            .map(stockProduitMapper::toDto);
    }

    /**
     * Delete the stockProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockProduit : {}", id);

        stockProduitRepository.deleteById(id);
    }
}
