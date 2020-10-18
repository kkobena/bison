package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.MotifService;
import com.kobe.nucleus.domain.Motif;
import com.kobe.nucleus.repository.MotifRepository;
import com.kobe.nucleus.service.dto.MotifDTO;
import com.kobe.nucleus.service.mapper.MotifMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Motif}.
 */
@Service
@Transactional
public class MotifServiceImpl implements MotifService {

    private final Logger log = LoggerFactory.getLogger(MotifServiceImpl.class);

    private final MotifRepository motifRepository;

    private final MotifMapper motifMapper;

    public MotifServiceImpl(MotifRepository motifRepository, MotifMapper motifMapper) {
        this.motifRepository = motifRepository;
        this.motifMapper = motifMapper;
    }

    /**
     * Save a motif.
     *
     * @param motifDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MotifDTO save(MotifDTO motifDTO) {
        log.debug("Request to save Motif : {}", motifDTO);
        Motif motif = motifMapper.toEntity(motifDTO);
        motif = motifRepository.save(motif);
        return motifMapper.toDto(motif);
    }

    /**
     * Get all the motifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MotifDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Motifs");
        return motifRepository.findAll(pageable)
            .map(motifMapper::toDto);
    }


    /**
     * Get one motif by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MotifDTO> findOne(Long id) {
        log.debug("Request to get Motif : {}", id);
        return motifRepository.findById(id)
            .map(motifMapper::toDto);
    }

    /**
     * Delete the motif by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Motif : {}", id);

        motifRepository.deleteById(id);
    }
}
