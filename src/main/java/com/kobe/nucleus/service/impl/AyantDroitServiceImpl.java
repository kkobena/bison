package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.AyantDroitService;
import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.repository.AyantDroitRepository;
import com.kobe.nucleus.service.dto.AyantDroitDTO;
import com.kobe.nucleus.service.mapper.AyantDroitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AyantDroit}.
 */
@Service
@Transactional
public class AyantDroitServiceImpl implements AyantDroitService {

    private final Logger log = LoggerFactory.getLogger(AyantDroitServiceImpl.class);

    private final AyantDroitRepository ayantDroitRepository;

    private final AyantDroitMapper ayantDroitMapper;

    public AyantDroitServiceImpl(AyantDroitRepository ayantDroitRepository, AyantDroitMapper ayantDroitMapper) {
        this.ayantDroitRepository = ayantDroitRepository;
        this.ayantDroitMapper = ayantDroitMapper;
    }

    /**
     * Save a ayantDroit.
     *
     * @param ayantDroitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AyantDroitDTO save(AyantDroitDTO ayantDroitDTO) {
        log.debug("Request to save AyantDroit : {}", ayantDroitDTO);
        AyantDroit ayantDroit = ayantDroitMapper.toEntity(ayantDroitDTO);
        ayantDroit = ayantDroitRepository.save(ayantDroit);
        return ayantDroitMapper.toDto(ayantDroit);
    }

    /**
     * Get all the ayantDroits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AyantDroitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AyantDroits");
        return ayantDroitRepository.findAll(pageable)
            .map(ayantDroitMapper::toDto);
    }

    /**
     * Get one ayantDroit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AyantDroitDTO> findOne(Long id) {
        log.debug("Request to get AyantDroit : {}", id);
        return ayantDroitRepository.findById(id)
            .map(ayantDroitMapper::toDto);
    }

    /**
     * Delete the ayantDroit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AyantDroit : {}", id);
        ayantDroitRepository.deleteById(id);
    }
}
