package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.GammeProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.GammeProduit}.
 */
public interface GammeProduitService {

    /**
     * Save a gammeProduit.
     *
     * @param gammeProduitDTO the entity to save.
     * @return the persisted entity.
     */
    GammeProduitDTO save(GammeProduitDTO gammeProduitDTO);

    /**
     * Get all the gammeProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GammeProduitDTO> findAll(String search,Pageable pageable);


    /**
     * Get the "id" gammeProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GammeProduitDTO> findOne(Long id);

    /**
     * Delete the "id" gammeProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    void importation(InputStream inputStream);
}
