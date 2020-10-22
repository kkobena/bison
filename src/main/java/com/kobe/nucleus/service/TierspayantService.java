package com.kobe.nucleus.service;

import com.kobe.nucleus.service.dto.ResponseDTO;
import com.kobe.nucleus.service.dto.TierspayantDTO;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Tierspayant}.
 */
public interface TierspayantService {

    /**
     * Save a tierspayant.
     *
     * @param tierspayantDTO the entity to save.
     * @return the persisted entity.
     */
    TierspayantDTO save(TierspayantDTO tierspayantDTO);

    /**
     * Get all the tierspayants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TierspayantDTO> findAll(String search,Pageable pageable);


    /**
     * Get the "id" tierspayant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TierspayantDTO> findOne(Long id);

    /**
     * Delete the "id" tierspayant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    /**
     *  process  the csv imported file
     * @param intput
     * @return ResponseDTO
     * @throws ParseException 
     */
    ResponseDTO processCsvStream(InputStream intput) throws IOException, ParseException;
    
    /**
     * Update a tierspayant.
     *
     * @param tierspayantDTO the entity to update.
     * @return the persisted entity.
     */
    TierspayantDTO update(TierspayantDTO tierspayantDTO);
}
