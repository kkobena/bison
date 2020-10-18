package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.TypeMvtCaisseService;
import com.kobe.nucleus.domain.TypeMvtCaisse;
import com.kobe.nucleus.repository.TypeMvtCaisseRepository;
import com.kobe.nucleus.service.dto.TypeMvtCaisseDTO;
import com.kobe.nucleus.service.mapper.TypeMvtCaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeMvtCaisse}.
 */
@Service
@Transactional
public class TypeMvtCaisseServiceImpl implements TypeMvtCaisseService {

    private final Logger log = LoggerFactory.getLogger(TypeMvtCaisseServiceImpl.class);

    private final TypeMvtCaisseRepository typeMvtCaisseRepository;

    private final TypeMvtCaisseMapper typeMvtCaisseMapper;

    public TypeMvtCaisseServiceImpl(TypeMvtCaisseRepository typeMvtCaisseRepository, TypeMvtCaisseMapper typeMvtCaisseMapper) {
        this.typeMvtCaisseRepository = typeMvtCaisseRepository;
        this.typeMvtCaisseMapper = typeMvtCaisseMapper;
    }

    /**
     * Save a typeMvtCaisse.
     *
     * @param typeMvtCaisseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TypeMvtCaisseDTO save(TypeMvtCaisseDTO typeMvtCaisseDTO) {
        log.debug("Request to save TypeMvtCaisse : {}", typeMvtCaisseDTO);
        TypeMvtCaisse typeMvtCaisse = typeMvtCaisseMapper.toEntity(typeMvtCaisseDTO);
        typeMvtCaisse = typeMvtCaisseRepository.save(typeMvtCaisse);
        return typeMvtCaisseMapper.toDto(typeMvtCaisse);
    }

    /**
     * Get all the typeMvtCaisses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeMvtCaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeMvtCaisses");
        return typeMvtCaisseRepository.findAll(pageable)
            .map(typeMvtCaisseMapper::toDto);
    }


    /**
     * Get one typeMvtCaisse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeMvtCaisseDTO> findOne(Long id) {
        log.debug("Request to get TypeMvtCaisse : {}", id);
        return typeMvtCaisseRepository.findById(id)
            .map(typeMvtCaisseMapper::toDto);
    }

    /**
     * Delete the typeMvtCaisse by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeMvtCaisse : {}", id);

        typeMvtCaisseRepository.deleteById(id);
    }
}
