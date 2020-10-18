package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.DetailsInventaireService;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.repository.DetailsInventaireRepository;
import com.kobe.nucleus.service.dto.DetailsInventaireDTO;
import com.kobe.nucleus.service.mapper.DetailsInventaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetailsInventaire}.
 */
@Service
@Transactional
public class DetailsInventaireServiceImpl implements DetailsInventaireService {

    private final Logger log = LoggerFactory.getLogger(DetailsInventaireServiceImpl.class);

    private final DetailsInventaireRepository detailsInventaireRepository;

    private final DetailsInventaireMapper detailsInventaireMapper;

    public DetailsInventaireServiceImpl(DetailsInventaireRepository detailsInventaireRepository, DetailsInventaireMapper detailsInventaireMapper) {
        this.detailsInventaireRepository = detailsInventaireRepository;
        this.detailsInventaireMapper = detailsInventaireMapper;
    }

    /**
     * Save a detailsInventaire.
     *
     * @param detailsInventaireDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DetailsInventaireDTO save(DetailsInventaireDTO detailsInventaireDTO) {
        log.debug("Request to save DetailsInventaire : {}", detailsInventaireDTO);
        DetailsInventaire detailsInventaire = detailsInventaireMapper.toEntity(detailsInventaireDTO);
        detailsInventaire = detailsInventaireRepository.save(detailsInventaire);
        return detailsInventaireMapper.toDto(detailsInventaire);
    }

    /**
     * Get all the detailsInventaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetailsInventaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailsInventaires");
        return detailsInventaireRepository.findAll(pageable)
            .map(detailsInventaireMapper::toDto);
    }


    /**
     * Get one detailsInventaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DetailsInventaireDTO> findOne(Long id) {
        log.debug("Request to get DetailsInventaire : {}", id);
        return detailsInventaireRepository.findById(id)
            .map(detailsInventaireMapper::toDto);
    }

    /**
     * Delete the detailsInventaire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailsInventaire : {}", id);

        detailsInventaireRepository.deleteById(id);
    }
}
