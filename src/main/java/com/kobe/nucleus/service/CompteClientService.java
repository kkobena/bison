package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CompteClientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.CompteClient}.
 */
public interface CompteClientService {

    /**
     * Save a compteClient.
     *
     * @param compteClientDTO the entity to save.
     * @return the persisted entity.
     */
    CompteClientDTO save(CompteClientDTO compteClientDTO);

    /**
     * Get all the compteClients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CompteClientDTO> findAll(Pageable pageable);


    /**
     * Get the "id" compteClient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompteClientDTO> findOne(Long id);

    /**
     * Delete the "id" compteClient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
