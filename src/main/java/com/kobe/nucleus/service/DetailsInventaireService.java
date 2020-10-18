package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.DetailsInventaireDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.DetailsInventaire}.
 */
public interface DetailsInventaireService {

    /**
     * Save a detailsInventaire.
     *
     * @param detailsInventaireDTO the entity to save.
     * @return the persisted entity.
     */
    DetailsInventaireDTO save(DetailsInventaireDTO detailsInventaireDTO);

    /**
     * Get all the detailsInventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailsInventaireDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detailsInventaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailsInventaireDTO> findOne(Long id);

    /**
     * Delete the "id" detailsInventaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
