package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.DeconditionService;
import com.kobe.nucleus.domain.Decondition;
import com.kobe.nucleus.repository.DeconditionRepository;
import com.kobe.nucleus.service.dto.DeconditionDTO;
import com.kobe.nucleus.service.mapper.DeconditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Decondition}.
 */
@Service
@Transactional
public class DeconditionServiceImpl implements DeconditionService {

    private final Logger log = LoggerFactory.getLogger(DeconditionServiceImpl.class);

    private final DeconditionRepository deconditionRepository;

    private final DeconditionMapper deconditionMapper;

    public DeconditionServiceImpl(DeconditionRepository deconditionRepository, DeconditionMapper deconditionMapper) {
        this.deconditionRepository = deconditionRepository;
        this.deconditionMapper = deconditionMapper;
    }

    /**
     * Save a decondition.
     *
     * @param deconditionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DeconditionDTO save(DeconditionDTO deconditionDTO) {
        log.debug("Request to save Decondition : {}", deconditionDTO);
        Decondition decondition = deconditionMapper.toEntity(deconditionDTO);
        decondition = deconditionRepository.save(decondition);
        return deconditionMapper.toDto(decondition);
    }

    /**
     * Get all the deconditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeconditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Deconditions");
        return deconditionRepository.findAll(pageable)
            .map(deconditionMapper::toDto);
    }


    /**
     * Get one decondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeconditionDTO> findOne(Long id) {
        log.debug("Request to get Decondition : {}", id);
        return deconditionRepository.findById(id)
            .map(deconditionMapper::toDto);
    }

    /**
     * Delete the decondition by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Decondition : {}", id);

        deconditionRepository.deleteById(id);
    }
}
