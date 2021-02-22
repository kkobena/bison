package com.kobe.nucleus.service;

import java.util.List;
import java.util.Optional;

import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.service.dto.MagasinDTO;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Magasin}.
 */
public interface MagasinService {

	/**
	 * Save a magasin.
	 *
	 * @param magasinDTO the entity to save.
	 * @return the persisted entity.
	 */
	MagasinDTO save(MagasinDTO magasinDTO);

	/**
	 * Get all the magasins.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	List<MagasinDTO> findAll();

	/**
	 * Get the "id" magasin.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<MagasinDTO> findOne(Long id);

	/**
	 * Delete the "id" magasin.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	List<MagasinDTO> findByTypeMagasin(TypeMagasin typeMagasin);

	List<MagasinDTO> findAllMagasinAndDepots();

}
