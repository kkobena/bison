package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.RisqueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Risque}.
 */
public interface RisqueService {

    /**
     * Save a risque.
     *
     * @param risqueDTO the entity to save.
     * @return the persisted entity.
     */
    RisqueDTO save(RisqueDTO risqueDTO);

    /**
     * Get all the risques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RisqueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" risque.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RisqueDTO> findOne(Long id);

    /**
     * Delete the "id" risque.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
