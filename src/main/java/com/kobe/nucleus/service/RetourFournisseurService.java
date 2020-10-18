package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.RetourFournisseurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.RetourFournisseur}.
 */
public interface RetourFournisseurService {

    /**
     * Save a retourFournisseur.
     *
     * @param retourFournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    RetourFournisseurDTO save(RetourFournisseurDTO retourFournisseurDTO);

    /**
     * Get all the retourFournisseurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RetourFournisseurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" retourFournisseur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RetourFournisseurDTO> findOne(Long id);

    /**
     * Delete the "id" retourFournisseur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
