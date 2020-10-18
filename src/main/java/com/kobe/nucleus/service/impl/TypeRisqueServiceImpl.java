package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.TypeRisqueService;
import com.kobe.nucleus.domain.TypeRisque;
import com.kobe.nucleus.repository.TypeRisqueRepository;
import com.kobe.nucleus.service.dto.TypeRisqueDTO;
import com.kobe.nucleus.service.mapper.TypeRisqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeRisque}.
 */
@Service
@Transactional
public class TypeRisqueServiceImpl implements TypeRisqueService {

    private final Logger log = LoggerFactory.getLogger(TypeRisqueServiceImpl.class);

    private final TypeRisqueRepository typeRisqueRepository;

    private final TypeRisqueMapper typeRisqueMapper;

    public TypeRisqueServiceImpl(TypeRisqueRepository typeRisqueRepository, TypeRisqueMapper typeRisqueMapper) {
        this.typeRisqueRepository = typeRisqueRepository;
        this.typeRisqueMapper = typeRisqueMapper;
    }

    /**
     * Save a typeRisque.
     *
     * @param typeRisqueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TypeRisqueDTO save(TypeRisqueDTO typeRisqueDTO) {
        log.debug("Request to save TypeRisque : {}", typeRisqueDTO);
        TypeRisque typeRisque = typeRisqueMapper.toEntity(typeRisqueDTO);
        typeRisque = typeRisqueRepository.save(typeRisque);
        return typeRisqueMapper.toDto(typeRisque);
    }

    /**
     * Get all the typeRisques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeRisqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeRisques");
        return typeRisqueRepository.findAll(pageable)
            .map(typeRisqueMapper::toDto);
    }


    /**
     * Get one typeRisque by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeRisqueDTO> findOne(Long id) {
        log.debug("Request to get TypeRisque : {}", id);
        return typeRisqueRepository.findById(id)
            .map(typeRisqueMapper::toDto);
    }

    /**
     * Delete the typeRisque by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeRisque : {}", id);

        typeRisqueRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeRisqueDTO> findByCodeOrLibelle(String  code,String libelle, Pageable pageable) {
        return typeRisqueRepository.findByCodeOrLibelleAllIgnoreCaseStartingWithOrderByCodeAsc(code,libelle,pageable)
            .map(typeRisqueMapper::toDto);
    }
}
