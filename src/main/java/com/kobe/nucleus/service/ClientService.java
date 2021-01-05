package com.kobe.nucleus.service;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.ClientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Client}.
 */
public interface ClientService {

	/**
	 * Save a client.
	 *
	 * @param clientDTO the entity to save.
	 * @return the persisted entity.
	 */
	ClientDTO save(ClientDTO clientDTO) throws Exception;

	/**
	 * Get all the clients.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<ClientDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" client.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<ClientDTO> findOne(Long id);

	/**
	 * Delete the "id" client.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	/**
	 * Get all the clients with ayantDroits and compteClients.
	 * 
	 * @search Search criteria
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	List<ClientDTO> findAll(String search, TypeClient typeClient, Status status);

	void activate(Long id, boolean enable);
}
