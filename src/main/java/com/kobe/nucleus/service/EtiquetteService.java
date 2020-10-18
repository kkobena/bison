package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.EtiquetteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Etiquette}.
 */
public interface EtiquetteService {

    /**
     * Save a etiquette.
     *
     * @param etiquetteDTO the entity to save.
     * @return the persisted entity.
     */
    EtiquetteDTO save(EtiquetteDTO etiquetteDTO);

    /**
     * Get all the etiquettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EtiquetteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etiquette.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtiquetteDTO> findOne(Long id);

    /**
     * Delete the "id" etiquette.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
