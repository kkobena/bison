package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.RetourItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.RetourItem}.
 */
public interface RetourItemService {

    /**
     * Save a retourItem.
     *
     * @param retourItemDTO the entity to save.
     * @return the persisted entity.
     */
    RetourItemDTO save(RetourItemDTO retourItemDTO);

    /**
     * Get all the retourItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RetourItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" retourItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RetourItemDTO> findOne(Long id);

    /**
     * Delete the "id" retourItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
