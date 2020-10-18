package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.DetailsAjustementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.DetailsAjustement}.
 */
public interface DetailsAjustementService {

    /**
     * Save a detailsAjustement.
     *
     * @param detailsAjustementDTO the entity to save.
     * @return the persisted entity.
     */
    DetailsAjustementDTO save(DetailsAjustementDTO detailsAjustementDTO);

    /**
     * Get all the detailsAjustements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailsAjustementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detailsAjustement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailsAjustementDTO> findOne(Long id);

    /**
     * Delete the "id" detailsAjustement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
