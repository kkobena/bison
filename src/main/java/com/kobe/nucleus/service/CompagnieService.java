package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CompagnieDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Compagnie}.
 */
public interface CompagnieService {

    /**
     * Save a compagnie.
     *
     * @param compagnieDTO the entity to save.
     * @return the persisted entity.
     */
    CompagnieDTO save(CompagnieDTO compagnieDTO);

    /**
     * Get all the compagnies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CompagnieDTO> findAll(Pageable pageable);


    /**
     * Get the "id" compagnie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompagnieDTO> findOne(Long id);

    /**
     * Delete the "id" compagnie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
