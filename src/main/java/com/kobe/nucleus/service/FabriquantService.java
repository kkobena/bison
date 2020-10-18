package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.FabriquantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Fabriquant}.
 */
public interface FabriquantService {

    /**
     * Save a fabriquant.
     *
     * @param fabriquantDTO the entity to save.
     * @return the persisted entity.
     */
    FabriquantDTO save(FabriquantDTO fabriquantDTO);

    /**
     * Get all the fabriquants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FabriquantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fabriquant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FabriquantDTO> findOne(Long id);

    /**
     * Delete the "id" fabriquant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
