package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.MotifDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Motif}.
 */
public interface MotifService {

    /**
     * Save a motif.
     *
     * @param motifDTO the entity to save.
     * @return the persisted entity.
     */
    MotifDTO save(MotifDTO motifDTO);

    /**
     * Get all the motifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MotifDTO> findAll(Pageable pageable);


    /**
     * Get the "id" motif.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MotifDTO> findOne(Long id);

    /**
     * Delete the "id" motif.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
