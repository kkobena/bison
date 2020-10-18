package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.RoleUtilisateurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.RoleUtilisateur}.
 */
public interface RoleUtilisateurService {

    /**
     * Save a roleUtilisateur.
     *
     * @param roleUtilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    RoleUtilisateurDTO save(RoleUtilisateurDTO roleUtilisateurDTO);

    /**
     * Get all the roleUtilisateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoleUtilisateurDTO> findAll(Pageable pageable);

    /**
     * Get all the roleUtilisateurs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<RoleUtilisateurDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" roleUtilisateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleUtilisateurDTO> findOne(Long id);

    /**
     * Delete the "id" roleUtilisateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
