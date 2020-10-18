package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.ModelFactureService;
import com.kobe.nucleus.domain.ModelFacture;
import com.kobe.nucleus.repository.ModelFactureRepository;
import com.kobe.nucleus.service.dto.ModelFactureDTO;
import com.kobe.nucleus.service.mapper.ModelFactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModelFacture}.
 */
@Service
@Transactional
public class ModelFactureServiceImpl implements ModelFactureService {

    private final Logger log = LoggerFactory.getLogger(ModelFactureServiceImpl.class);

    private final ModelFactureRepository modelFactureRepository;

    private final ModelFactureMapper modelFactureMapper;

    public ModelFactureServiceImpl(ModelFactureRepository modelFactureRepository, ModelFactureMapper modelFactureMapper) {
        this.modelFactureRepository = modelFactureRepository;
        this.modelFactureMapper = modelFactureMapper;
    }

    /**
     * Save a modelFacture.
     *
     * @param modelFactureDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ModelFactureDTO save(ModelFactureDTO modelFactureDTO) {
        log.debug("Request to save ModelFacture : {}", modelFactureDTO);
        ModelFacture modelFacture = modelFactureMapper.toEntity(modelFactureDTO);
        modelFacture = modelFactureRepository.save(modelFacture);
        return modelFactureMapper.toDto(modelFacture);
    }

    /**
     * Get all the modelFactures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ModelFactureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ModelFactures");
        return modelFactureRepository.findAll(pageable)
            .map(modelFactureMapper::toDto);
    }

    /**
     * Get one modelFacture by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ModelFactureDTO> findOne(Long id) {
        log.debug("Request to get ModelFacture : {}", id);
        return modelFactureRepository.findById(id)
            .map(modelFactureMapper::toDto);
    }

    /**
     * Delete the modelFacture by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ModelFacture : {}", id);
        modelFactureRepository.deleteById(id);
    }
}
