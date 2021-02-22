package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.FournisseurProduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.FournisseurProduit}.
 */
public interface FournisseurProduitService {

    /**
     * Save a fournisseurProduit.
     *
     * @param fournisseurProduitDTO the entity to save.
     * @return the persisted entity.
     */
    FournisseurProduitDTO save(FournisseurProduitDTO fournisseurProduitDTO);

    /**
     * Get all the fournisseurProduits.
     *
     * @return the list of entities.
     */
    List<FournisseurProduitDTO> findAll();


    /**
     * Get the "id" fournisseurProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FournisseurProduitDTO> findOne(Long id);

    /**
     * Delete the "id" fournisseurProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
