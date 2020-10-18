package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.FactureItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.FactureItem}.
 */
public interface FactureItemService {

    /**
     * Save a factureItem.
     *
     * @param factureItemDTO the entity to save.
     * @return the persisted entity.
     */
    FactureItemDTO save(FactureItemDTO factureItemDTO);

    /**
     * Get all the factureItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FactureItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" factureItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactureItemDTO> findOne(Long id);

    /**
     * Delete the "id" factureItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
