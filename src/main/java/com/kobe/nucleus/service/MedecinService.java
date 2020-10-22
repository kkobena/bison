package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.MedecinDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Medecin}.
 */
public interface MedecinService {

    /**
     * Save a medecin.
     *
     * @param medecinDTO the entity to save.
     * @return the persisted entity.
     */
    MedecinDTO save(MedecinDTO medecinDTO);

    /**
     * Get all the medecins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedecinDTO> findAll(Pageable pageable);


    /**
     * Get the "id" medecin.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedecinDTO> findOne(Long id);

    /**
     * Delete the "id" medecin.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
