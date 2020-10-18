package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.TypeEtiquetteService;
import com.kobe.nucleus.domain.TypeEtiquette;
import com.kobe.nucleus.repository.TypeEtiquetteRepository;
import com.kobe.nucleus.service.dto.TypeEtiquetteDTO;
import com.kobe.nucleus.service.mapper.TypeEtiquetteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeEtiquette}.
 */
@Service
@Transactional
public class TypeEtiquetteServiceImpl implements TypeEtiquetteService {

    private final Logger log = LoggerFactory.getLogger(TypeEtiquetteServiceImpl.class);

    private final TypeEtiquetteRepository typeEtiquetteRepository;

    private final TypeEtiquetteMapper typeEtiquetteMapper;

    public TypeEtiquetteServiceImpl(TypeEtiquetteRepository typeEtiquetteRepository, TypeEtiquetteMapper typeEtiquetteMapper) {
        this.typeEtiquetteRepository = typeEtiquetteRepository;
        this.typeEtiquetteMapper = typeEtiquetteMapper;
    }

    /**
     * Save a typeEtiquette.
     *
     * @param typeEtiquetteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TypeEtiquetteDTO save(TypeEtiquetteDTO typeEtiquetteDTO) {
        log.debug("Request to save TypeEtiquette : {}", typeEtiquetteDTO);
        TypeEtiquette typeEtiquette = typeEtiquetteMapper.toEntity(typeEtiquetteDTO);
        typeEtiquette = typeEtiquetteRepository.save(typeEtiquette);
        return typeEtiquetteMapper.toDto(typeEtiquette);
    }

    /**
     * Get all the typeEtiquettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeEtiquetteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeEtiquettes");
        return typeEtiquetteRepository.findAll(pageable)
            .map(typeEtiquetteMapper::toDto);
    }


    /**
     * Get one typeEtiquette by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeEtiquetteDTO> findOne(Long id) {
        log.debug("Request to get TypeEtiquette : {}", id);
        return typeEtiquetteRepository.findById(id)
            .map(typeEtiquetteMapper::toDto);
    }

    /**
     * Delete the typeEtiquette by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeEtiquette : {}", id);

        typeEtiquetteRepository.deleteById(id);
    }
}
