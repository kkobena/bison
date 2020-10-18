package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.MvtProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.MvtProduit}.
 */
public interface MvtProduitService {

    /**
     * Save a mvtProduit.
     *
     * @param mvtProduitDTO the entity to save.
     * @return the persisted entity.
     */
    MvtProduitDTO save(MvtProduitDTO mvtProduitDTO);

    /**
     * Get all the mvtProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MvtProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mvtProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MvtProduitDTO> findOne(Long id);

    /**
     * Delete the "id" mvtProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
