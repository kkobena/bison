package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.MvtsProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.MvtsProduit}.
 */
public interface MvtsProduitService {

    /**
     * Save a mvtsProduit.
     *
     * @param mvtsProduitDTO the entity to save.
     * @return the persisted entity.
     */
    MvtsProduitDTO save(MvtsProduitDTO mvtsProduitDTO);

    /**
     * Get all the mvtsProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MvtsProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mvtsProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MvtsProduitDTO> findOne(Long id);

    /**
     * Delete the "id" mvtsProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
