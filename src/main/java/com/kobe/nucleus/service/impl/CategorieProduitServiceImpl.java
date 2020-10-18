package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CategorieProduitService;
import com.kobe.nucleus.domain.CategorieProduit;
import com.kobe.nucleus.repository.CategorieProduitRepository;
import com.kobe.nucleus.service.dto.CategorieProduitDTO;
import com.kobe.nucleus.service.mapper.CategorieProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieProduit}.
 */
@Service
@Transactional
public class CategorieProduitServiceImpl implements CategorieProduitService {

    private final Logger log = LoggerFactory.getLogger(CategorieProduitServiceImpl.class);

    private final CategorieProduitRepository categorieProduitRepository;

    private final CategorieProduitMapper categorieProduitMapper;

    public CategorieProduitServiceImpl(CategorieProduitRepository categorieProduitRepository, CategorieProduitMapper categorieProduitMapper) {
        this.categorieProduitRepository = categorieProduitRepository;
        this.categorieProduitMapper = categorieProduitMapper;
    }

    /**
     * Save a categorieProduit.
     *
     * @param categorieProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategorieProduitDTO save(CategorieProduitDTO categorieProduitDTO) {
        log.debug("Request to save CategorieProduit : {}", categorieProduitDTO);
        CategorieProduit categorieProduit = categorieProduitMapper.toEntity(categorieProduitDTO);
        categorieProduit = categorieProduitRepository.save(categorieProduit);
        return categorieProduitMapper.toDto(categorieProduit);
    }

    /**
     * Get all the categorieProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategorieProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieProduits");
        return categorieProduitRepository.findAll(pageable)
            .map(categorieProduitMapper::toDto);
    }


    /**
     * Get one categorieProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieProduitDTO> findOne(Long id) {
        log.debug("Request to get CategorieProduit : {}", id);
        return categorieProduitRepository.findById(id)
            .map(categorieProduitMapper::toDto);
    }

    /**
     * Delete the categorieProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieProduit : {}", id);

        categorieProduitRepository.deleteById(id);
    }
}
