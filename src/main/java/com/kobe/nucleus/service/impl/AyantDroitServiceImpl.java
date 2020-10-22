package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.AyantDroitService;
import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.repository.AyantDroitRepository;
import com.kobe.nucleus.service.dto.AyantDroitDTO;
import com.kobe.nucleus.service.mapper.AyantDroitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AyantDroit}.
 */
@Service
@Transactional
public class AyantDroitServiceImpl implements AyantDroitService {

    private final Logger log = LoggerFactory.getLogger(AyantDroitServiceImpl.class);

    private final AyantDroitRepository ayantDroitRepository;

    private final AyantDroitMapper ayantDroitMapper;

    public AyantDroitServiceImpl(AyantDroitRepository ayantDroitRepository, AyantDroitMapper ayantDroitMapper) {
        this.ayantDroitRepository = ayantDroitRepository;
        this.ayantDroitMapper = ayantDroitMapper;
    }

    @Override
    public AyantDroitDTO save(AyantDroitDTO ayantDroitDTO) {
        log.debug("Request to save AyantDroit : {}", ayantDroitDTO);
        AyantDroit ayantDroit = ayantDroitMapper.toEntity(ayantDroitDTO);
        ayantDroit = ayantDroitRepository.save(ayantDroit);
        return ayantDroitMapper.toDto(ayantDroit);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AyantDroitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AyantDroits");
        return ayantDroitRepository.findAll(pageable)
            .map(ayantDroitMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AyantDroitDTO> findOne(Long id) {
        log.debug("Request to get AyantDroit : {}", id);
        return ayantDroitRepository.findById(id)
            .map(ayantDroitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AyantDroit : {}", id);
        ayantDroitRepository.deleteById(id);
    }
}
