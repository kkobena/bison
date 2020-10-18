package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.RayonService;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.repository.RayonRepository;
import com.kobe.nucleus.service.dto.RayonDTO;
import com.kobe.nucleus.service.mapper.RayonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Rayon}.
 */
@Service
@Transactional
public class RayonServiceImpl implements RayonService {

    private final Logger log = LoggerFactory.getLogger(RayonServiceImpl.class);

    private final RayonRepository rayonRepository;

    private final RayonMapper rayonMapper;

    public RayonServiceImpl(RayonRepository rayonRepository, RayonMapper rayonMapper) {
        this.rayonRepository = rayonRepository;
        this.rayonMapper = rayonMapper;
    }

    /**
     * Save a rayon.
     *
     * @param rayonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RayonDTO save(RayonDTO rayonDTO) {
        log.debug("Request to save Rayon : {}", rayonDTO);
        Rayon rayon = rayonMapper.toEntity(rayonDTO);
        rayon = rayonRepository.save(rayon);
        return rayonMapper.toDto(rayon);
    }

    /**
     * Get all the rayons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RayonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rayons");
        return rayonRepository.findAll(pageable)
            .map(rayonMapper::toDto);
    }


    /**
     * Get one rayon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RayonDTO> findOne(Long id) {
        log.debug("Request to get Rayon : {}", id);
        return rayonRepository.findById(id)
            .map(rayonMapper::toDto);
    }

    /**
     * Delete the rayon by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rayon : {}", id);

        rayonRepository.deleteById(id);
    }
}
