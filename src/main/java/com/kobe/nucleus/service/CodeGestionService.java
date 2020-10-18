package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.CodeGestionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.CodeGestion}.
 */
public interface CodeGestionService {

    /**
     * Save a codeGestion.
     *
     * @param codeGestionDTO the entity to save.
     * @return the persisted entity.
     */
    CodeGestionDTO save(CodeGestionDTO codeGestionDTO);

    /**
     * Get all the codeGestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CodeGestionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" codeGestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CodeGestionDTO> findOne(Long id);

    /**
     * Delete the "id" codeGestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
