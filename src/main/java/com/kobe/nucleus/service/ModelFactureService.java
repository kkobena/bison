package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.ModelFactureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.ModelFacture}.
 */
public interface ModelFactureService {

    /**
     * Save a modelFacture.
     *
     * @param modelFactureDTO the entity to save.
     * @return the persisted entity.
     */
    ModelFactureDTO save(ModelFactureDTO modelFactureDTO);

    /**
     * Get all the modelFactures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ModelFactureDTO> findAll(Pageable pageable);

    /**
     * Get the "id" modelFacture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ModelFactureDTO> findOne(Long id);

    /**
     * Delete the "id" modelFacture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
