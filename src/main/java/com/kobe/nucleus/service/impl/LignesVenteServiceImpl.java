package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.LignesVenteService;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.repository.LignesVenteRepository;
import com.kobe.nucleus.service.dto.LignesVenteDTO;
import com.kobe.nucleus.service.mapper.LignesVenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LignesVente}.
 */
@Service
@Transactional
public class LignesVenteServiceImpl implements LignesVenteService {

    private final Logger log = LoggerFactory.getLogger(LignesVenteServiceImpl.class);

    private final LignesVenteRepository lignesVenteRepository;

    private final LignesVenteMapper lignesVenteMapper;

    public LignesVenteServiceImpl(LignesVenteRepository lignesVenteRepository, LignesVenteMapper lignesVenteMapper) {
        this.lignesVenteRepository = lignesVenteRepository;
        this.lignesVenteMapper = lignesVenteMapper;
    }

    /**
     * Save a lignesVente.
     *
     * @param lignesVenteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LignesVenteDTO save(LignesVenteDTO lignesVenteDTO) {
        log.debug("Request to save LignesVente : {}", lignesVenteDTO);
        LignesVente lignesVente = lignesVenteMapper.toEntity(lignesVenteDTO);
        lignesVente = lignesVenteRepository.save(lignesVente);
        return lignesVenteMapper.toDto(lignesVente);
    }

    /**
     * Get all the lignesVentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LignesVenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LignesVentes");
        return lignesVenteRepository.findAll(pageable)
            .map(lignesVenteMapper::toDto);
    }


    /**
     * Get one lignesVente by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LignesVenteDTO> findOne(Long id) {
        log.debug("Request to get LignesVente : {}", id);
        return lignesVenteRepository.findById(id)
            .map(lignesVenteMapper::toDto);
    }

    /**
     * Delete the lignesVente by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LignesVente : {}", id);

        lignesVenteRepository.deleteById(id);
    }
}
