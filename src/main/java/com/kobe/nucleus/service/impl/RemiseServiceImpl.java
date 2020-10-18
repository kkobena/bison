package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.RemiseService;
import com.kobe.nucleus.domain.Remise;
import com.kobe.nucleus.repository.RemiseRepository;
import com.kobe.nucleus.service.dto.RemiseDTO;
import com.kobe.nucleus.service.mapper.RemiseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Remise}.
 */
@Service
@Transactional
public class RemiseServiceImpl implements RemiseService {

    private final Logger log = LoggerFactory.getLogger(RemiseServiceImpl.class);

    private final RemiseRepository remiseRepository;

    private final RemiseMapper remiseMapper;

    public RemiseServiceImpl(RemiseRepository remiseRepository, RemiseMapper remiseMapper) {
        this.remiseRepository = remiseRepository;
        this.remiseMapper = remiseMapper;
    }

    /**
     * Save a remise.
     *
     * @param remiseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RemiseDTO save(RemiseDTO remiseDTO) {
        log.debug("Request to save Remise : {}", remiseDTO);
        Remise remise = remiseMapper.toEntity(remiseDTO);
        remise.setStatus(Status.ACTIVE);
        remise = remiseRepository.save(remise);
        return remiseMapper.toDto(remise);
    }

    /**
     * Get all the remises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RemiseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Remises");
        return remiseRepository.findByStatus(Status.DESACTIVE,pageable)
            .map(remiseMapper::toDto);
    }


    /**
     * Get one remise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RemiseDTO> findOne(Long id) {
        log.debug("Request to get Remise : {}", id);
        return remiseRepository.findById(id)
            .map(remiseMapper::toDto);
    }

    /**
     * Delete the remise by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Remise : {}", id);

        remiseRepository.deleteById(id);
    }

    @Override
    public void disable(Long id) {
       remiseRepository.findById(id).ifPresent(remise -> {
          remise.setStatus(Status.ACTIVE);
          remiseRepository.save(remise);

       });

    }
}
