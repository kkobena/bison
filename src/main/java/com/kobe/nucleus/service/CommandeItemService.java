package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CommandeItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.CommandeItem}.
 */
public interface CommandeItemService {

    /**
     * Save a commandeItem.
     *
     * @param commandeItemDTO the entity to save.
     * @return the persisted entity.
     */
    CommandeItemDTO save(CommandeItemDTO commandeItemDTO);

    /**
     * Get all the commandeItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommandeItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commandeItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommandeItemDTO> findOne(Long id);

    /**
     * Delete the "id" commandeItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
