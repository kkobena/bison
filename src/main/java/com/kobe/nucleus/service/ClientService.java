package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.ClientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
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
    ClientDTO save(ClientDTO clientDTO);

    /**
     * Get all the clients.
     *
     * @param pageable the pagination information.
     * @param search the filter value
     * @return the list of entities.
     */
    Page<ClientDTO> findAll(String search,Pageable pageable);


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
     * Get all the clients By tiers-payant ID.
     *
     * @param pageable the pagination information.
     * @param search the filter value
     * @param tiersPayantId  tiers-payant ID
     * @return the list of entities.
     */
    Page<ClientDTO> findAllByTiersPayant(@NotNull Integer tiersPayantId ,String search, Pageable pageable);
}
