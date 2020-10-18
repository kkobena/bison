package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.InventaireDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Inventaire}.
 */
public interface InventaireService {

    /**
     * Save a inventaire.
     *
     * @param inventaireDTO the entity to save.
     * @return the persisted entity.
     */
    InventaireDTO save(InventaireDTO inventaireDTO);

    /**
     * Get all the inventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InventaireDTO> findAll(Pageable pageable);


    /**
     * Get the "id" inventaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InventaireDTO> findOne(Long id);

    /**
     * Delete the "id" inventaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
