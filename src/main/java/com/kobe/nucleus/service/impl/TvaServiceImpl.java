package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.TvaService;
import com.kobe.nucleus.domain.Tva;
import com.kobe.nucleus.repository.TvaRepository;
import com.kobe.nucleus.service.dto.TvaDTO;
import com.kobe.nucleus.service.mapper.TvaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tva}.
 */
@Service
@Transactional
public class TvaServiceImpl implements TvaService {

    private final Logger log = LoggerFactory.getLogger(TvaServiceImpl.class);

    private final TvaRepository tvaRepository;

    private final TvaMapper tvaMapper;

    public TvaServiceImpl(TvaRepository tvaRepository, TvaMapper tvaMapper) {
        this.tvaRepository = tvaRepository;
        this.tvaMapper = tvaMapper;
    }

    /**
     * Save a tva.
     *
     * @param tvaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TvaDTO save(TvaDTO tvaDTO) {
        log.debug("Request to save Tva : {}", tvaDTO);
        Tva tva = tvaMapper.toEntity(tvaDTO);
        tva = tvaRepository.save(tva);
        return tvaMapper.toDto(tva);
    }

    /**
     * Get all the tvas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TvaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tvas");
        return tvaRepository.findAll(pageable)
            .map(tvaMapper::toDto);
    }


    /**
     * Get one tva by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TvaDTO> findOne(Long id) {
        log.debug("Request to get Tva : {}", id);
        return tvaRepository.findById(id)
            .map(tvaMapper::toDto);
    }

    /**
     * Delete the tva by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tva : {}", id);

        tvaRepository.deleteById(id);
    }
}
