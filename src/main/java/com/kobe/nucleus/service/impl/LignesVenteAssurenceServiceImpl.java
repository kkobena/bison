package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.LignesVenteAssurenceService;
import com.kobe.nucleus.domain.LignesVenteAssurence;
import com.kobe.nucleus.repository.LignesVenteAssurenceRepository;
import com.kobe.nucleus.service.dto.LignesVenteAssurenceDTO;
import com.kobe.nucleus.service.mapper.LignesVenteAssurenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LignesVenteAssurence}.
 */
@Service
@Transactional
public class LignesVenteAssurenceServiceImpl implements LignesVenteAssurenceService {

    private final Logger log = LoggerFactory.getLogger(LignesVenteAssurenceServiceImpl.class);

    private final LignesVenteAssurenceRepository lignesVenteAssurenceRepository;

    private final LignesVenteAssurenceMapper lignesVenteAssurenceMapper;

    public LignesVenteAssurenceServiceImpl(LignesVenteAssurenceRepository lignesVenteAssurenceRepository, LignesVenteAssurenceMapper lignesVenteAssurenceMapper) {
        this.lignesVenteAssurenceRepository = lignesVenteAssurenceRepository;
        this.lignesVenteAssurenceMapper = lignesVenteAssurenceMapper;
    }

    /**
     * Save a lignesVenteAssurence.
     *
     * @param lignesVenteAssurenceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LignesVenteAssurenceDTO save(LignesVenteAssurenceDTO lignesVenteAssurenceDTO) {
        log.debug("Request to save LignesVenteAssurence : {}", lignesVenteAssurenceDTO);
        LignesVenteAssurence lignesVenteAssurence = lignesVenteAssurenceMapper.toEntity(lignesVenteAssurenceDTO);
        lignesVenteAssurence = lignesVenteAssurenceRepository.save(lignesVenteAssurence);
        return lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);
    }

    /**
     * Get all the lignesVenteAssurences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LignesVenteAssurenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LignesVenteAssurences");
        return lignesVenteAssurenceRepository.findAll(pageable)
            .map(lignesVenteAssurenceMapper::toDto);
    }


    /**
     * Get one lignesVenteAssurence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LignesVenteAssurenceDTO> findOne(Long id) {
        log.debug("Request to get LignesVenteAssurence : {}", id);
        return lignesVenteAssurenceRepository.findById(id)
            .map(lignesVenteAssurenceMapper::toDto);
    }

    /**
     * Delete the lignesVenteAssurence by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LignesVenteAssurence : {}", id);

        lignesVenteAssurenceRepository.deleteById(id);
    }
}
