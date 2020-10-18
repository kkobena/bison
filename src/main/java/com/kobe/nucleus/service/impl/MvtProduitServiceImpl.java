package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.MvtProduitService;
import com.kobe.nucleus.domain.MvtProduit;
import com.kobe.nucleus.repository.MvtProduitRepository;
import com.kobe.nucleus.service.dto.MvtProduitDTO;
import com.kobe.nucleus.service.mapper.MvtProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MvtProduit}.
 */
@Service
@Transactional
public class MvtProduitServiceImpl implements MvtProduitService {

    private final Logger log = LoggerFactory.getLogger(MvtProduitServiceImpl.class);

    private final MvtProduitRepository mvtProduitRepository;

    private final MvtProduitMapper mvtProduitMapper;

    public MvtProduitServiceImpl(MvtProduitRepository mvtProduitRepository, MvtProduitMapper mvtProduitMapper) {
        this.mvtProduitRepository = mvtProduitRepository;
        this.mvtProduitMapper = mvtProduitMapper;
    }

    /**
     * Save a mvtProduit.
     *
     * @param mvtProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MvtProduitDTO save(MvtProduitDTO mvtProduitDTO) {
        log.debug("Request to save MvtProduit : {}", mvtProduitDTO);
        MvtProduit mvtProduit = mvtProduitMapper.toEntity(mvtProduitDTO);
        mvtProduit = mvtProduitRepository.save(mvtProduit);
        return mvtProduitMapper.toDto(mvtProduit);
    }

    /**
     * Get all the mvtProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MvtProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MvtProduits");
        return mvtProduitRepository.findAll(pageable)
            .map(mvtProduitMapper::toDto);
    }


    /**
     * Get one mvtProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MvtProduitDTO> findOne(Long id) {
        log.debug("Request to get MvtProduit : {}", id);
        return mvtProduitRepository.findById(id)
            .map(mvtProduitMapper::toDto);
    }

    /**
     * Delete the mvtProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MvtProduit : {}", id);

        mvtProduitRepository.deleteById(id);
    }
}
