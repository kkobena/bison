package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CategorieAyantDroitService;
import com.kobe.nucleus.domain.CategorieAyantDroit;
import com.kobe.nucleus.repository.CategorieAyantDroitRepository;
import com.kobe.nucleus.service.dto.CategorieAyantDroitDTO;
import com.kobe.nucleus.service.mapper.CategorieAyantDroitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieAyantDroit}.
 */
@Service
@Transactional
public class CategorieAyantDroitServiceImpl implements CategorieAyantDroitService {

    private final Logger log = LoggerFactory.getLogger(CategorieAyantDroitServiceImpl.class);

    private final CategorieAyantDroitRepository categorieAyantDroitRepository;

    private final CategorieAyantDroitMapper categorieAyantDroitMapper;

    public CategorieAyantDroitServiceImpl(CategorieAyantDroitRepository categorieAyantDroitRepository, CategorieAyantDroitMapper categorieAyantDroitMapper) {
        this.categorieAyantDroitRepository = categorieAyantDroitRepository;
        this.categorieAyantDroitMapper = categorieAyantDroitMapper;
    }

    /**
     * Save a categorieAyantDroit.
     *
     * @param categorieAyantDroitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategorieAyantDroitDTO save(CategorieAyantDroitDTO categorieAyantDroitDTO) {
        log.debug("Request to save CategorieAyantDroit : {}", categorieAyantDroitDTO);
        CategorieAyantDroit categorieAyantDroit = categorieAyantDroitMapper.toEntity(categorieAyantDroitDTO);
        categorieAyantDroit = categorieAyantDroitRepository.save(categorieAyantDroit);
        return categorieAyantDroitMapper.toDto(categorieAyantDroit);
    }

    /**
     * Get all the categorieAyantDroits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategorieAyantDroitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieAyantDroits");
        return categorieAyantDroitRepository.findAll(pageable)
            .map(categorieAyantDroitMapper::toDto);
    }

    /**
     * Get one categorieAyantDroit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieAyantDroitDTO> findOne(Long id) {
        log.debug("Request to get CategorieAyantDroit : {}", id);
        return categorieAyantDroitRepository.findById(id)
            .map(categorieAyantDroitMapper::toDto);
    }

    /**
     * Delete the categorieAyantDroit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieAyantDroit : {}", id);
        categorieAyantDroitRepository.deleteById(id);
    }
}
