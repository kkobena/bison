package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CodeGestionService;
import com.kobe.nucleus.domain.CodeGestion;
import com.kobe.nucleus.repository.CodeGestionRepository;
import com.kobe.nucleus.service.dto.CodeGestionDTO;
import com.kobe.nucleus.service.mapper.CodeGestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CodeGestion}.
 */
@Service
@Transactional
public class CodeGestionServiceImpl implements CodeGestionService {

    private final Logger log = LoggerFactory.getLogger(CodeGestionServiceImpl.class);

    private final CodeGestionRepository codeGestionRepository;

    private final CodeGestionMapper codeGestionMapper;

    public CodeGestionServiceImpl(CodeGestionRepository codeGestionRepository, CodeGestionMapper codeGestionMapper) {
        this.codeGestionRepository = codeGestionRepository;
        this.codeGestionMapper = codeGestionMapper;
    }

    /**
     * Save a codeGestion.
     *
     * @param codeGestionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CodeGestionDTO save(CodeGestionDTO codeGestionDTO) {
        log.debug("Request to save CodeGestion : {}", codeGestionDTO);
        CodeGestion codeGestion = codeGestionMapper.toEntity(codeGestionDTO);
        codeGestion = codeGestionRepository.save(codeGestion);
        return codeGestionMapper.toDto(codeGestion);
    }

    /**
     * Get all the codeGestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CodeGestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CodeGestions");
        return codeGestionRepository.findAll(pageable)
            .map(codeGestionMapper::toDto);
    }


    /**
     * Get one codeGestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CodeGestionDTO> findOne(Long id) {
        log.debug("Request to get CodeGestion : {}", id);
        return codeGestionRepository.findById(id)
            .map(codeGestionMapper::toDto);
    }

    /**
     * Delete the codeGestion by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CodeGestion : {}", id);

        codeGestionRepository.deleteById(id);
    }
}
