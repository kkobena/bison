package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.TypeRisqueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.TypeRisque}.
 */
public interface TypeRisqueService {

    /**
     * Save a typeRisque.
     *
     * @param typeRisqueDTO the entity to save.
     * @return the persisted entity.
     */
    TypeRisqueDTO save(TypeRisqueDTO typeRisqueDTO);

    /**
     * Get all the typeRisques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeRisqueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeRisque.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeRisqueDTO> findOne(Long id);

    /**
     * Delete the "id" typeRisque.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<TypeRisqueDTO> findByCodeOrLibelle(String  code,String libelle,Pageable pageable);
}
