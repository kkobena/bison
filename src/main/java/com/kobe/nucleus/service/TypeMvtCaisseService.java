package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.TypeMvtCaisseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.TypeMvtCaisse}.
 */
public interface TypeMvtCaisseService {

    /**
     * Save a typeMvtCaisse.
     *
     * @param typeMvtCaisseDTO the entity to save.
     * @return the persisted entity.
     */
    TypeMvtCaisseDTO save(TypeMvtCaisseDTO typeMvtCaisseDTO);

    /**
     * Get all the typeMvtCaisses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeMvtCaisseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeMvtCaisse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeMvtCaisseDTO> findOne(Long id);

    /**
     * Delete the "id" typeMvtCaisse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
