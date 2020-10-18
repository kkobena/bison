package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.StockProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.StockProduit}.
 */
public interface StockProduitService {

    /**
     * Save a stockProduit.
     *
     * @param stockProduitDTO the entity to save.
     * @return the persisted entity.
     */
    StockProduitDTO save(StockProduitDTO stockProduitDTO);

    /**
     * Get all the stockProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockProduitDTO> findOne(Long id);

    /**
     * Delete the "id" stockProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
