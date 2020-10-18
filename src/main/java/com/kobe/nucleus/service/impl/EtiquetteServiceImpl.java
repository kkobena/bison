package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.EtiquetteService;
import com.kobe.nucleus.domain.Etiquette;
import com.kobe.nucleus.repository.EtiquetteRepository;
import com.kobe.nucleus.service.dto.EtiquetteDTO;
import com.kobe.nucleus.service.mapper.EtiquetteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Etiquette}.
 */
@Service
@Transactional
public class EtiquetteServiceImpl implements EtiquetteService {

    private final Logger log = LoggerFactory.getLogger(EtiquetteServiceImpl.class);

    private final EtiquetteRepository etiquetteRepository;

    private final EtiquetteMapper etiquetteMapper;

    public EtiquetteServiceImpl(EtiquetteRepository etiquetteRepository, EtiquetteMapper etiquetteMapper) {
        this.etiquetteRepository = etiquetteRepository;
        this.etiquetteMapper = etiquetteMapper;
    }

    /**
     * Save a etiquette.
     *
     * @param etiquetteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtiquetteDTO save(EtiquetteDTO etiquetteDTO) {
        log.debug("Request to save Etiquette : {}", etiquetteDTO);
        Etiquette etiquette = etiquetteMapper.toEntity(etiquetteDTO);
        etiquette = etiquetteRepository.save(etiquette);
        return etiquetteMapper.toDto(etiquette);
    }

    /**
     * Get all the etiquettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtiquetteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etiquettes");
        return etiquetteRepository.findAll(pageable)
            .map(etiquetteMapper::toDto);
    }


    /**
     * Get one etiquette by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtiquetteDTO> findOne(Long id) {
        log.debug("Request to get Etiquette : {}", id);
        return etiquetteRepository.findById(id)
            .map(etiquetteMapper::toDto);
    }

    /**
     * Delete the etiquette by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etiquette : {}", id);

        etiquetteRepository.deleteById(id);
    }
}
