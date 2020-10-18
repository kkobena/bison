package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.LotDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Lot}.
 */
public interface LotService {

    /**
     * Save a lot.
     *
     * @param lotDTO the entity to save.
     * @return the persisted entity.
     */
    LotDTO save(LotDTO lotDTO);

    /**
     * Get all the lots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LotDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lot.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LotDTO> findOne(Long id);

    /**
     * Delete the "id" lot.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
