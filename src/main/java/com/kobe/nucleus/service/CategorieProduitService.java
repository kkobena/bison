package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CategorieProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.CategorieProduit}.
 */
public interface CategorieProduitService {

    /**
     * Save a categorieProduit.
     *
     * @param categorieProduitDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieProduitDTO save(CategorieProduitDTO categorieProduitDTO);

    /**
     * Get all the categorieProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieProduitDTO> findOne(Long id);

    /**
     * Delete the "id" categorieProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
