package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.TypeInventaireDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.TypeInventaire}.
 */
public interface TypeInventaireService {

    /**
     * Save a typeInventaire.
     *
     * @param typeInventaireDTO the entity to save.
     * @return the persisted entity.
     */
    TypeInventaireDTO save(TypeInventaireDTO typeInventaireDTO);

    /**
     * Get all the typeInventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeInventaireDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeInventaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeInventaireDTO> findOne(Long id);

    /**
     * Delete the "id" typeInventaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
