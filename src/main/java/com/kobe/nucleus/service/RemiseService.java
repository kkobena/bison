package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.RemiseClientDTO;
import com.kobe.nucleus.service.dto.RemiseDTO;
import com.kobe.nucleus.service.dto.RemiseProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Remise}.
 */
public interface RemiseService {

    /**
     * Save a remise.
     *
     * @param remiseDTO the entity to save.
     * @return the persisted entity.
     */
	RemiseProduitDTO save(RemiseProduitDTO remiseProduitDTO);

    /**
     * Get all the remises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RemiseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" remise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RemiseDTO> findOne(Long id);

    /**
     * Delete the "id" remise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Disable the "id" remise.
     *
     * @param id the id of the entity.
     */
    void disable(Long id);
    
    RemiseClientDTO save(RemiseClientDTO remiseClientDTO);
}
