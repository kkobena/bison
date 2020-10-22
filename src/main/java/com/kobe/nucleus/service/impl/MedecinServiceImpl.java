package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.MedecinService;
import com.kobe.nucleus.domain.Medecin;
import com.kobe.nucleus.repository.MedecinRepository;
import com.kobe.nucleus.service.dto.MedecinDTO;
import com.kobe.nucleus.service.mapper.MedecinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Medecin}.
 */
@Service
@Transactional
public class MedecinServiceImpl implements MedecinService {

    private final Logger log = LoggerFactory.getLogger(MedecinServiceImpl.class);

    private final MedecinRepository medecinRepository;

    private final MedecinMapper medecinMapper;

    public MedecinServiceImpl(MedecinRepository medecinRepository, MedecinMapper medecinMapper) {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
    }

    @Override
    public MedecinDTO save(MedecinDTO medecinDTO) {
        log.debug("Request to save Medecin : {}", medecinDTO);
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        medecin = medecinRepository.save(medecin);
        return medecinMapper.toDto(medecin);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedecinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Medecins");
        return medecinRepository.findAll(pageable)
            .map(medecinMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MedecinDTO> findOne(Long id) {
        log.debug("Request to get Medecin : {}", id);
        return medecinRepository.findById(id)
            .map(medecinMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Medecin : {}", id);
        medecinRepository.deleteById(id);
    }
}
