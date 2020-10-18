package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.RetourFournisseurService;
import com.kobe.nucleus.domain.RetourFournisseur;
import com.kobe.nucleus.repository.RetourFournisseurRepository;
import com.kobe.nucleus.service.dto.RetourFournisseurDTO;
import com.kobe.nucleus.service.mapper.RetourFournisseurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RetourFournisseur}.
 */
@Service
@Transactional
public class RetourFournisseurServiceImpl implements RetourFournisseurService {

    private final Logger log = LoggerFactory.getLogger(RetourFournisseurServiceImpl.class);

    private final RetourFournisseurRepository retourFournisseurRepository;

    private final RetourFournisseurMapper retourFournisseurMapper;

    public RetourFournisseurServiceImpl(RetourFournisseurRepository retourFournisseurRepository, RetourFournisseurMapper retourFournisseurMapper) {
        this.retourFournisseurRepository = retourFournisseurRepository;
        this.retourFournisseurMapper = retourFournisseurMapper;
    }

    /**
     * Save a retourFournisseur.
     *
     * @param retourFournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RetourFournisseurDTO save(RetourFournisseurDTO retourFournisseurDTO) {
        log.debug("Request to save RetourFournisseur : {}", retourFournisseurDTO);
        RetourFournisseur retourFournisseur = retourFournisseurMapper.toEntity(retourFournisseurDTO);
        retourFournisseur = retourFournisseurRepository.save(retourFournisseur);
        return retourFournisseurMapper.toDto(retourFournisseur);
    }

    /**
     * Get all the retourFournisseurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RetourFournisseurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RetourFournisseurs");
        return retourFournisseurRepository.findAll(pageable)
            .map(retourFournisseurMapper::toDto);
    }


    /**
     * Get one retourFournisseur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RetourFournisseurDTO> findOne(Long id) {
        log.debug("Request to get RetourFournisseur : {}", id);
        return retourFournisseurRepository.findById(id)
            .map(retourFournisseurMapper::toDto);
    }

    /**
     * Delete the retourFournisseur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RetourFournisseur : {}", id);

        retourFournisseurRepository.deleteById(id);
    }
}
