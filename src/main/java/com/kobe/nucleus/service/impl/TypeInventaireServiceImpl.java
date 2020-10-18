package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.TypeInventaireService;
import com.kobe.nucleus.domain.TypeInventaire;
import com.kobe.nucleus.repository.TypeInventaireRepository;
import com.kobe.nucleus.service.dto.TypeInventaireDTO;
import com.kobe.nucleus.service.mapper.TypeInventaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeInventaire}.
 */
@Service
@Transactional
public class TypeInventaireServiceImpl implements TypeInventaireService {

    private final Logger log = LoggerFactory.getLogger(TypeInventaireServiceImpl.class);

    private final TypeInventaireRepository typeInventaireRepository;

    private final TypeInventaireMapper typeInventaireMapper;

    public TypeInventaireServiceImpl(TypeInventaireRepository typeInventaireRepository, TypeInventaireMapper typeInventaireMapper) {
        this.typeInventaireRepository = typeInventaireRepository;
        this.typeInventaireMapper = typeInventaireMapper;
    }

    /**
     * Save a typeInventaire.
     *
     * @param typeInventaireDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TypeInventaireDTO save(TypeInventaireDTO typeInventaireDTO) {
        log.debug("Request to save TypeInventaire : {}", typeInventaireDTO);
        TypeInventaire typeInventaire = typeInventaireMapper.toEntity(typeInventaireDTO);
        typeInventaire = typeInventaireRepository.save(typeInventaire);
        return typeInventaireMapper.toDto(typeInventaire);
    }

    /**
     * Get all the typeInventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeInventaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeInventaires");
        return typeInventaireRepository.findAll(pageable)
            .map(typeInventaireMapper::toDto);
    }


    /**
     * Get one typeInventaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeInventaireDTO> findOne(Long id) {
        log.debug("Request to get TypeInventaire : {}", id);
        return typeInventaireRepository.findById(id)
            .map(typeInventaireMapper::toDto);
    }

    /**
     * Delete the typeInventaire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeInventaire : {}", id);

        typeInventaireRepository.deleteById(id);
    }
}
