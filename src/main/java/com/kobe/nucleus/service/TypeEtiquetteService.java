package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.TypeEtiquetteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.TypeEtiquette}.
 */
public interface TypeEtiquetteService {

    /**
     * Save a typeEtiquette.
     *
     * @param typeEtiquetteDTO the entity to save.
     * @return the persisted entity.
     */
    TypeEtiquetteDTO save(TypeEtiquetteDTO typeEtiquetteDTO);

    /**
     * Get all the typeEtiquettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeEtiquetteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeEtiquette.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeEtiquetteDTO> findOne(Long id);

    /**
     * Delete the "id" typeEtiquette.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
