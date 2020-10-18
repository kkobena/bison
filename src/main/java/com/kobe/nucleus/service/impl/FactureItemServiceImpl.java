package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.FactureItemService;
import com.kobe.nucleus.domain.FactureItem;
import com.kobe.nucleus.repository.FactureItemRepository;
import com.kobe.nucleus.service.dto.FactureItemDTO;
import com.kobe.nucleus.service.mapper.FactureItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FactureItem}.
 */
@Service
@Transactional
public class FactureItemServiceImpl implements FactureItemService {

    private final Logger log = LoggerFactory.getLogger(FactureItemServiceImpl.class);

    private final FactureItemRepository factureItemRepository;

    private final FactureItemMapper factureItemMapper;

    public FactureItemServiceImpl(FactureItemRepository factureItemRepository, FactureItemMapper factureItemMapper) {
        this.factureItemRepository = factureItemRepository;
        this.factureItemMapper = factureItemMapper;
    }

    /**
     * Save a factureItem.
     *
     * @param factureItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FactureItemDTO save(FactureItemDTO factureItemDTO) {
        log.debug("Request to save FactureItem : {}", factureItemDTO);
        FactureItem factureItem = factureItemMapper.toEntity(factureItemDTO);
        factureItem = factureItemRepository.save(factureItem);
        return factureItemMapper.toDto(factureItem);
    }

    /**
     * Get all the factureItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FactureItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactureItems");
        return factureItemRepository.findAll(pageable)
            .map(factureItemMapper::toDto);
    }


    /**
     * Get one factureItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FactureItemDTO> findOne(Long id) {
        log.debug("Request to get FactureItem : {}", id);
        return factureItemRepository.findById(id)
            .map(factureItemMapper::toDto);
    }

    /**
     * Delete the factureItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FactureItem : {}", id);

        factureItemRepository.deleteById(id);
    }
}
