package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.FabriquantService;
import com.kobe.nucleus.domain.Fabriquant;
import com.kobe.nucleus.repository.FabriquantRepository;
import com.kobe.nucleus.service.dto.FabriquantDTO;
import com.kobe.nucleus.service.mapper.FabriquantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Fabriquant}.
 */
@Service
@Transactional
public class FabriquantServiceImpl implements FabriquantService {

    private final Logger log = LoggerFactory.getLogger(FabriquantServiceImpl.class);

    private final FabriquantRepository fabriquantRepository;

    private final FabriquantMapper fabriquantMapper;

    public FabriquantServiceImpl(FabriquantRepository fabriquantRepository, FabriquantMapper fabriquantMapper) {
        this.fabriquantRepository = fabriquantRepository;
        this.fabriquantMapper = fabriquantMapper;
    }

    /**
     * Save a fabriquant.
     *
     * @param fabriquantDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FabriquantDTO save(FabriquantDTO fabriquantDTO) {
        log.debug("Request to save Fabriquant : {}", fabriquantDTO);
        Fabriquant fabriquant = fabriquantMapper.toEntity(fabriquantDTO);
        fabriquant = fabriquantRepository.save(fabriquant);
        return fabriquantMapper.toDto(fabriquant);
    }

    /**
     * Get all the fabriquants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FabriquantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fabriquants");
        return fabriquantRepository.findAll(pageable)
            .map(fabriquantMapper::toDto);
    }


    /**
     * Get one fabriquant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FabriquantDTO> findOne(Long id) {
        log.debug("Request to get Fabriquant : {}", id);
        return fabriquantRepository.findById(id)
            .map(fabriquantMapper::toDto);
    }

    /**
     * Delete the fabriquant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fabriquant : {}", id);

        fabriquantRepository.deleteById(id);
    }
}
