package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.FamilleProduitService;
import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.repository.FamilleProduitRepository;
import com.kobe.nucleus.service.dto.FamilleProduitDTO;
import com.kobe.nucleus.service.mapper.FamilleProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FamilleProduit}.
 */
@Service
@Transactional
public class FamilleProduitServiceImpl implements FamilleProduitService {

    private final Logger log = LoggerFactory.getLogger(FamilleProduitServiceImpl.class);

    private final FamilleProduitRepository familleProduitRepository;

    private final FamilleProduitMapper familleProduitMapper;

    public FamilleProduitServiceImpl(FamilleProduitRepository familleProduitRepository, FamilleProduitMapper familleProduitMapper) {
        this.familleProduitRepository = familleProduitRepository;
        this.familleProduitMapper = familleProduitMapper;
    }

    /**
     * Save a familleProduit.
     *
     * @param familleProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FamilleProduitDTO save(FamilleProduitDTO familleProduitDTO) {
        log.debug("Request to save FamilleProduit : {}", familleProduitDTO);
        FamilleProduit familleProduit = familleProduitMapper.toEntity(familleProduitDTO);
        familleProduit = familleProduitRepository.save(familleProduit);
        return familleProduitMapper.toDto(familleProduit);
    }

    /**
     * Get all the familleProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FamilleProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FamilleProduits");
        return familleProduitRepository.findAll(pageable)
            .map(familleProduitMapper::toDto);
    }


    /**
     * Get one familleProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FamilleProduitDTO> findOne(Long id) {
        log.debug("Request to get FamilleProduit : {}", id);
        return familleProduitRepository.findById(id)
            .map(familleProduitMapper::toDto);
    }

    /**
     * Delete the familleProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FamilleProduit : {}", id);

        familleProduitRepository.deleteById(id);
    }
}
