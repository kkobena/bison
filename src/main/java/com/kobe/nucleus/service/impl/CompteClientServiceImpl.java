package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CompteClientService;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.repository.CompteClientRepository;
import com.kobe.nucleus.service.dto.CompteClientDTO;
import com.kobe.nucleus.service.mapper.CompteClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CompteClient}.
 */
@Service
@Transactional
public class CompteClientServiceImpl implements CompteClientService {

    private final Logger log = LoggerFactory.getLogger(CompteClientServiceImpl.class);

    private final CompteClientRepository compteClientRepository;

    private final CompteClientMapper compteClientMapper;

    public CompteClientServiceImpl(CompteClientRepository compteClientRepository, CompteClientMapper compteClientMapper) {
        this.compteClientRepository = compteClientRepository;
        this.compteClientMapper = compteClientMapper;
    }

    @Override
    public CompteClientDTO save(CompteClientDTO compteClientDTO) {
        log.debug("Request to save CompteClient : {}", compteClientDTO);
        CompteClient compteClient = compteClientMapper.toEntity(compteClientDTO);
        compteClient = compteClientRepository.save(compteClient);
        return compteClientMapper.toDto(compteClient);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompteClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CompteClients");
        return compteClientRepository.findAll(pageable)
            .map(compteClientMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CompteClientDTO> findOne(Long id) {
        log.debug("Request to get CompteClient : {}", id);
        return compteClientRepository.findById(id)
            .map(compteClientMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompteClient : {}", id);
        compteClientRepository.deleteById(id);
    }
}
