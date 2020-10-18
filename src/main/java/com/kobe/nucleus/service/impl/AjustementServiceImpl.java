package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.AjustementService;
import com.kobe.nucleus.domain.Ajustement;
import com.kobe.nucleus.repository.AjustementRepository;
import com.kobe.nucleus.service.dto.AjustementDTO;
import com.kobe.nucleus.service.mapper.AjustementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ajustement}.
 */
@Service
@Transactional
public class AjustementServiceImpl implements AjustementService {

    private final Logger log = LoggerFactory.getLogger(AjustementServiceImpl.class);

    private final AjustementRepository ajustementRepository;

    private final AjustementMapper ajustementMapper;

    public AjustementServiceImpl(AjustementRepository ajustementRepository, AjustementMapper ajustementMapper) {
        this.ajustementRepository = ajustementRepository;
        this.ajustementMapper = ajustementMapper;
    }

    /**
     * Save a ajustement.
     *
     * @param ajustementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AjustementDTO save(AjustementDTO ajustementDTO) {
        log.debug("Request to save Ajustement : {}", ajustementDTO);
        Ajustement ajustement = ajustementMapper.toEntity(ajustementDTO);
        ajustement = ajustementRepository.save(ajustement);
        return ajustementMapper.toDto(ajustement);
    }

    /**
     * Get all the ajustements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AjustementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ajustements");
        return ajustementRepository.findAll(pageable)
            .map(ajustementMapper::toDto);
    }


    /**
     * Get one ajustement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AjustementDTO> findOne(Long id) {
        log.debug("Request to get Ajustement : {}", id);
        return ajustementRepository.findById(id)
            .map(ajustementMapper::toDto);
    }

    /**
     * Delete the ajustement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ajustement : {}", id);

        ajustementRepository.deleteById(id);
    }
}
