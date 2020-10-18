package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CategorieAyantDroitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.CategorieAyantDroit}.
 */
public interface CategorieAyantDroitService {

    /**
     * Save a categorieAyantDroit.
     *
     * @param categorieAyantDroitDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieAyantDroitDTO save(CategorieAyantDroitDTO categorieAyantDroitDTO);

    /**
     * Get all the categorieAyantDroits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieAyantDroitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieAyantDroit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieAyantDroitDTO> findOne(Long id);

    /**
     * Delete the "id" categorieAyantDroit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
