package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.FournisseurProduitService;
import com.kobe.nucleus.domain.FournisseurProduit;
import com.kobe.nucleus.repository.FournisseurProduitRepository;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;
import com.kobe.nucleus.service.mapper.FournisseurProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FournisseurProduit}.
 */
@Service
@Transactional
public class FournisseurProduitServiceImpl implements FournisseurProduitService {

    private final Logger log = LoggerFactory.getLogger(FournisseurProduitServiceImpl.class);

    private final FournisseurProduitRepository fournisseurProduitRepository;

    private final FournisseurProduitMapper fournisseurProduitMapper;

    public FournisseurProduitServiceImpl(FournisseurProduitRepository fournisseurProduitRepository, FournisseurProduitMapper fournisseurProduitMapper) {
        this.fournisseurProduitRepository = fournisseurProduitRepository;
        this.fournisseurProduitMapper = fournisseurProduitMapper;
    }

    /**
     * Save a fournisseurProduit.
     *
     * @param fournisseurProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FournisseurProduitDTO save(FournisseurProduitDTO fournisseurProduitDTO) {
        log.debug("Request to save FournisseurProduit : {}", fournisseurProduitDTO);
        FournisseurProduit fournisseurProduit = fournisseurProduitMapper.toEntity(fournisseurProduitDTO);
        fournisseurProduit = fournisseurProduitRepository.save(fournisseurProduit);
        return fournisseurProduitMapper.toDto(fournisseurProduit);
    }

    /**
     * Get all the fournisseurProduits.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FournisseurProduitDTO> findAll() {
        log.debug("Request to get all FournisseurProduits");
        return fournisseurProduitRepository.findAll().stream()
            .map(fournisseurProduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fournisseurProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FournisseurProduitDTO> findOne(Long id) {
        log.debug("Request to get FournisseurProduit : {}", id);
        return fournisseurProduitRepository.findById(id)
            .map(fournisseurProduitMapper::toDto);
    }

    /**
     * Delete the fournisseurProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FournisseurProduit : {}", id);

        fournisseurProduitRepository.deleteById(id);
    }
}
