package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.DeconditionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Decondition}.
 */
public interface DeconditionService {

    /**
     * Save a decondition.
     *
     * @param deconditionDTO the entity to save.
     * @return the persisted entity.
     */
    DeconditionDTO save(DeconditionDTO deconditionDTO);

    /**
     * Get all the deconditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeconditionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" decondition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeconditionDTO> findOne(Long id);

    /**
     * Delete the "id" decondition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
