package com.kobe.nucleus.service;

import com.kobe.nucleus.domain.Stockout;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Stockout}.
 */
public interface StockoutService {

    /**
     * Save a stockout.
     *
     * @param stockout the entity to save.
     * @return the persisted entity.
     */
    Stockout save(Stockout stockout);

    /**
     * Get all the stockouts.
     *
     * @return the list of entities.
     */
    List<Stockout> findAll();


    /**
     * Get the "id" stockout.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Stockout> findOne(Long id);

    /**
     * Delete the "id" stockout.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
