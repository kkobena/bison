package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.LignesVenteAssurenceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.LignesVenteAssurence}.
 */
public interface LignesVenteAssurenceService {

    /**
     * Save a lignesVenteAssurence.
     *
     * @param lignesVenteAssurenceDTO the entity to save.
     * @return the persisted entity.
     */
    LignesVenteAssurenceDTO save(LignesVenteAssurenceDTO lignesVenteAssurenceDTO);

    /**
     * Get all the lignesVenteAssurences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LignesVenteAssurenceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lignesVenteAssurence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LignesVenteAssurenceDTO> findOne(Long id);

    /**
     * Delete the "id" lignesVenteAssurence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
