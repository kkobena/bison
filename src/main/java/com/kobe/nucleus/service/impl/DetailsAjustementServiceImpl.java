package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.DetailsAjustementService;
import com.kobe.nucleus.domain.DetailsAjustement;
import com.kobe.nucleus.repository.DetailsAjustementRepository;
import com.kobe.nucleus.service.dto.DetailsAjustementDTO;
import com.kobe.nucleus.service.mapper.DetailsAjustementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetailsAjustement}.
 */
@Service
@Transactional
public class DetailsAjustementServiceImpl implements DetailsAjustementService {

    private final Logger log = LoggerFactory.getLogger(DetailsAjustementServiceImpl.class);

    private final DetailsAjustementRepository detailsAjustementRepository;

    private final DetailsAjustementMapper detailsAjustementMapper;

    public DetailsAjustementServiceImpl(DetailsAjustementRepository detailsAjustementRepository, DetailsAjustementMapper detailsAjustementMapper) {
        this.detailsAjustementRepository = detailsAjustementRepository;
        this.detailsAjustementMapper = detailsAjustementMapper;
    }

    /**
     * Save a detailsAjustement.
     *
     * @param detailsAjustementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DetailsAjustementDTO save(DetailsAjustementDTO detailsAjustementDTO) {
        log.debug("Request to save DetailsAjustement : {}", detailsAjustementDTO);
        DetailsAjustement detailsAjustement = detailsAjustementMapper.toEntity(detailsAjustementDTO);
        detailsAjustement = detailsAjustementRepository.save(detailsAjustement);
        return detailsAjustementMapper.toDto(detailsAjustement);
    }

    /**
     * Get all the detailsAjustements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetailsAjustementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailsAjustements");
        return detailsAjustementRepository.findAll(pageable)
            .map(detailsAjustementMapper::toDto);
    }


    /**
     * Get one detailsAjustement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DetailsAjustementDTO> findOne(Long id) {
        log.debug("Request to get DetailsAjustement : {}", id);
        return detailsAjustementRepository.findById(id)
            .map(detailsAjustementMapper::toDto);
    }

    /**
     * Delete the detailsAjustement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailsAjustement : {}", id);

        detailsAjustementRepository.deleteById(id);
    }
}
