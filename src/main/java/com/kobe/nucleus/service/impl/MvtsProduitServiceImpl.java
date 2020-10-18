package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.MvtsProduitService;
import com.kobe.nucleus.domain.MvtsProduit;
import com.kobe.nucleus.repository.MvtsProduitRepository;
import com.kobe.nucleus.service.dto.MvtsProduitDTO;
import com.kobe.nucleus.service.mapper.MvtsProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MvtsProduit}.
 */
@Service
@Transactional
public class MvtsProduitServiceImpl implements MvtsProduitService {

    private final Logger log = LoggerFactory.getLogger(MvtsProduitServiceImpl.class);

    private final MvtsProduitRepository mvtsProduitRepository;

    private final MvtsProduitMapper mvtsProduitMapper;

    public MvtsProduitServiceImpl(MvtsProduitRepository mvtsProduitRepository, MvtsProduitMapper mvtsProduitMapper) {
        this.mvtsProduitRepository = mvtsProduitRepository;
        this.mvtsProduitMapper = mvtsProduitMapper;
    }

    /**
     * Save a mvtsProduit.
     *
     * @param mvtsProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MvtsProduitDTO save(MvtsProduitDTO mvtsProduitDTO) {
        log.debug("Request to save MvtsProduit : {}", mvtsProduitDTO);
        MvtsProduit mvtsProduit = mvtsProduitMapper.toEntity(mvtsProduitDTO);
        mvtsProduit = mvtsProduitRepository.save(mvtsProduit);
        return mvtsProduitMapper.toDto(mvtsProduit);
    }

    /**
     * Get all the mvtsProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MvtsProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MvtsProduits");
        return mvtsProduitRepository.findAll(pageable)
            .map(mvtsProduitMapper::toDto);
    }


    /**
     * Get one mvtsProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MvtsProduitDTO> findOne(Long id) {
        log.debug("Request to get MvtsProduit : {}", id);
        return mvtsProduitRepository.findById(id)
            .map(mvtsProduitMapper::toDto);
    }

    /**
     * Delete the mvtsProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MvtsProduit : {}", id);

        mvtsProduitRepository.deleteById(id);
    }
}
