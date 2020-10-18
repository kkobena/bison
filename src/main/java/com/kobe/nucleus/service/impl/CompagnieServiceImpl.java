package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CompagnieService;
import com.kobe.nucleus.domain.Compagnie;
import com.kobe.nucleus.repository.CompagnieRepository;
import com.kobe.nucleus.service.dto.CompagnieDTO;
import com.kobe.nucleus.service.mapper.CompagnieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Compagnie}.
 */
@Service
@Transactional
public class CompagnieServiceImpl implements CompagnieService {

    private final Logger log = LoggerFactory.getLogger(CompagnieServiceImpl.class);

    private final CompagnieRepository compagnieRepository;

    private final CompagnieMapper compagnieMapper;

    public CompagnieServiceImpl(CompagnieRepository compagnieRepository, CompagnieMapper compagnieMapper) {
        this.compagnieRepository = compagnieRepository;
        this.compagnieMapper = compagnieMapper;
    }

    /**
     * Save a compagnie.
     *
     * @param compagnieDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompagnieDTO save(CompagnieDTO compagnieDTO) {
        log.debug("Request to save Compagnie : {}", compagnieDTO);
        Compagnie compagnie = compagnieMapper.toEntity(compagnieDTO);
        compagnie = compagnieRepository.save(compagnie);
        return compagnieMapper.toDto(compagnie);
    }

    /**
     * Get all the compagnies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompagnieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Compagnies");
        return compagnieRepository.findAll(pageable)
            .map(compagnieMapper::toDto);
    }


    /**
     * Get one compagnie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompagnieDTO> findOne(Long id) {
        log.debug("Request to get Compagnie : {}", id);
        return compagnieRepository.findById(id)
            .map(compagnieMapper::toDto);
    }

    /**
     * Delete the compagnie by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compagnie : {}", id);

        compagnieRepository.deleteById(id);
    }
}
