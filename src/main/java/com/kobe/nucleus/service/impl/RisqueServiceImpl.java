package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.RisqueService;
import com.kobe.nucleus.domain.Risque;
import com.kobe.nucleus.repository.RisqueRepository;
import com.kobe.nucleus.service.dto.RisqueDTO;
import com.kobe.nucleus.service.mapper.RisqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Risque}.
 */
@Service
@Transactional
public class RisqueServiceImpl implements RisqueService {

    private final Logger log = LoggerFactory.getLogger(RisqueServiceImpl.class);

    private final RisqueRepository risqueRepository;

    private final RisqueMapper risqueMapper;

    public RisqueServiceImpl(RisqueRepository risqueRepository, RisqueMapper risqueMapper) {
        this.risqueRepository = risqueRepository;
        this.risqueMapper = risqueMapper;
    }

    /**
     * Save a risque.
     *
     * @param risqueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RisqueDTO save(RisqueDTO risqueDTO) {
        log.debug("Request to save Risque : {}", risqueDTO);
        Risque risque = risqueMapper.toEntity(risqueDTO);
        risque = risqueRepository.save(risque);
        return risqueMapper.toDto(risque);
    }

    /**
     * Get all the risques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RisqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Risques");
        return risqueRepository.findAll(pageable)
            .map(risqueMapper::toDto);
    }


    /**
     * Get one risque by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RisqueDTO> findOne(Long id) {
        log.debug("Request to get Risque : {}", id);
        return risqueRepository.findById(id)
            .map(risqueMapper::toDto);
    }

    /**
     * Delete the risque by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Risque : {}", id);

        risqueRepository.deleteById(id);
    }
}
