package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.LignesVenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.LignesVente}.
 */
public interface LignesVenteService {

    /**
     * Save a lignesVente.
     *
     * @param lignesVenteDTO the entity to save.
     * @return the persisted entity.
     */
    LignesVenteDTO save(LignesVenteDTO lignesVenteDTO);

    /**
     * Get all the lignesVentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LignesVenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lignesVente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LignesVenteDTO> findOne(Long id);

    /**
     * Delete the "id" lignesVente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
