package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.FormProduitService;
import com.kobe.nucleus.domain.FormProduit;
import com.kobe.nucleus.repository.FormProduitRepository;
import com.kobe.nucleus.service.dto.FormProduitDTO;
import com.kobe.nucleus.service.mapper.FormProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FormProduit}.
 */
@Service
@Transactional
public class FormProduitServiceImpl implements FormProduitService {

    private final Logger log = LoggerFactory.getLogger(FormProduitServiceImpl.class);

    private final FormProduitRepository formProduitRepository;

    private final FormProduitMapper formProduitMapper;

    public FormProduitServiceImpl(FormProduitRepository formProduitRepository, FormProduitMapper formProduitMapper) {
        this.formProduitRepository = formProduitRepository;
        this.formProduitMapper = formProduitMapper;
    }

    /**
     * Save a formProduit.
     *
     * @param formProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormProduitDTO save(FormProduitDTO formProduitDTO) {
        log.debug("Request to save FormProduit : {}", formProduitDTO);
        FormProduit formProduit = formProduitMapper.toEntity(formProduitDTO);
        formProduit = formProduitRepository.save(formProduit);
        return formProduitMapper.toDto(formProduit);
    }

    /**
     * Get all the formProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FormProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FormProduits");
        return formProduitRepository.findAll(pageable)
            .map(formProduitMapper::toDto);
    }


    /**
     * Get one formProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormProduitDTO> findOne(Long id) {
        log.debug("Request to get FormProduit : {}", id);
        return formProduitRepository.findById(id)
            .map(formProduitMapper::toDto);
    }

    /**
     * Delete the formProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormProduit : {}", id);

        formProduitRepository.deleteById(id);
    }
}
