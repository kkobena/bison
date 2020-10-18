package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.AjustementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Ajustement}.
 */
public interface AjustementService {

    /**
     * Save a ajustement.
     *
     * @param ajustementDTO the entity to save.
     * @return the persisted entity.
     */
    AjustementDTO save(AjustementDTO ajustementDTO);

    /**
     * Get all the ajustements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AjustementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ajustement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AjustementDTO> findOne(Long id);

    /**
     * Delete the "id" ajustement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
