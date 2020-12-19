package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.AyantDroitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.AyantDroit}.
 */
public interface AyantDroitService {

    /**
     * Save a ayantDroit.
     *
     * @param ayantDroitDTO the entity to save.
     * @return the persisted entity.
     */
    AyantDroitDTO save(AyantDroitDTO ayantDroitDTO);

    /**
     * Get all the ayantDroits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AyantDroitDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ayantDroit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AyantDroitDTO> findOne(Long id);

    /**
     * Delete the "id" ayantDroit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
