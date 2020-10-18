package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.GroupeTierspayantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.GroupeTierspayant}.
 */
public interface GroupeTierspayantService {

    /**
     * Save a groupeTierspayant.
     *
     * @param groupeTierspayantDTO the entity to save.
     * @return the persisted entity.
     */
    GroupeTierspayantDTO save(GroupeTierspayantDTO groupeTierspayantDTO);

    /**
     * Get all the groupeTierspayants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GroupeTierspayantDTO> findAll(String search, Pageable pageable);


    /**
     * Get the "id" groupeTierspayant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GroupeTierspayantDTO> findOne(Long id);

    /**
     * Delete the "id" groupeTierspayant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void importGroupeTierspayant(InputStream inputStream) throws IOException;
}
