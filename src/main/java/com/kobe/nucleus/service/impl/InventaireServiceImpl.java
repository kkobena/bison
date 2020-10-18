package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.InventaireService;
import com.kobe.nucleus.domain.Inventaire;
import com.kobe.nucleus.repository.InventaireRepository;
import com.kobe.nucleus.service.dto.InventaireDTO;
import com.kobe.nucleus.service.mapper.InventaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Inventaire}.
 */
@Service
@Transactional
public class InventaireServiceImpl implements InventaireService {

    private final Logger log = LoggerFactory.getLogger(InventaireServiceImpl.class);

    private final InventaireRepository inventaireRepository;

    private final InventaireMapper inventaireMapper;

    public InventaireServiceImpl(InventaireRepository inventaireRepository, InventaireMapper inventaireMapper) {
        this.inventaireRepository = inventaireRepository;
        this.inventaireMapper = inventaireMapper;
    }

    /**
     * Save a inventaire.
     *
     * @param inventaireDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InventaireDTO save(InventaireDTO inventaireDTO) {
        log.debug("Request to save Inventaire : {}", inventaireDTO);
        Inventaire inventaire = inventaireMapper.toEntity(inventaireDTO);
        inventaire = inventaireRepository.save(inventaire);
        return inventaireMapper.toDto(inventaire);
    }

    /**
     * Get all the inventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InventaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Inventaires");
        return inventaireRepository.findAll(pageable)
            .map(inventaireMapper::toDto);
    }


    /**
     * Get one inventaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InventaireDTO> findOne(Long id) {
        log.debug("Request to get Inventaire : {}", id);
        return inventaireRepository.findById(id)
            .map(inventaireMapper::toDto);
    }

    /**
     * Delete the inventaire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Inventaire : {}", id);

        inventaireRepository.deleteById(id);
    }
}
