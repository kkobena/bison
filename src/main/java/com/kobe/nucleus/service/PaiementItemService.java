package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.PaiementItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.PaiementItem}.
 */
public interface PaiementItemService {

    /**
     * Save a paiementItem.
     *
     * @param paiementItemDTO the entity to save.
     * @return the persisted entity.
     */
    PaiementItemDTO save(PaiementItemDTO paiementItemDTO);

    /**
     * Get all the paiementItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaiementItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" paiementItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaiementItemDTO> findOne(Long id);

    /**
     * Delete the "id" paiementItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
