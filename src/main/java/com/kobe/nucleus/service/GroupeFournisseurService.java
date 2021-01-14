package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.GroupeFournisseurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Service Interface for managing
 * {@link com.kobe.nucleus.domain.GroupeFournisseur}.
 */
public interface GroupeFournisseurService {

	/**
	 * Save a groupeFournisseur.
	 *
	 * @param groupeFournisseurDTO the entity to save.
	 * @return the persisted entity.
	 */
	GroupeFournisseurDTO save(GroupeFournisseurDTO groupeFournisseurDTO);

	/**
	 * Get all the groupeFournisseurs.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<GroupeFournisseurDTO> findAll(String search,Pageable pageable);

	/**
	 * Get the "id" groupeFournisseur.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<GroupeFournisseurDTO> findOne(Long id);

	/**
	 * Delete the "id" groupeFournisseur.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	void importation(InputStream inputStream) throws IOException;
}
