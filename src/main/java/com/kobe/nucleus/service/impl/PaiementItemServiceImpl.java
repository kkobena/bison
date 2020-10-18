package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.PaiementItemService;
import com.kobe.nucleus.domain.PaiementItem;
import com.kobe.nucleus.repository.PaiementItemRepository;
import com.kobe.nucleus.service.dto.PaiementItemDTO;
import com.kobe.nucleus.service.mapper.PaiementItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PaiementItem}.
 */
@Service
@Transactional
public class PaiementItemServiceImpl implements PaiementItemService {

    private final Logger log = LoggerFactory.getLogger(PaiementItemServiceImpl.class);

    private final PaiementItemRepository paiementItemRepository;

    private final PaiementItemMapper paiementItemMapper;

    public PaiementItemServiceImpl(PaiementItemRepository paiementItemRepository, PaiementItemMapper paiementItemMapper) {
        this.paiementItemRepository = paiementItemRepository;
        this.paiementItemMapper = paiementItemMapper;
    }

    /**
     * Save a paiementItem.
     *
     * @param paiementItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaiementItemDTO save(PaiementItemDTO paiementItemDTO) {
        log.debug("Request to save PaiementItem : {}", paiementItemDTO);
        PaiementItem paiementItem = paiementItemMapper.toEntity(paiementItemDTO);
        paiementItem = paiementItemRepository.save(paiementItem);
        return paiementItemMapper.toDto(paiementItem);
    }

    /**
     * Get all the paiementItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaiementItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PaiementItems");
        return paiementItemRepository.findAll(pageable)
            .map(paiementItemMapper::toDto);
    }


    /**
     * Get one paiementItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaiementItemDTO> findOne(Long id) {
        log.debug("Request to get PaiementItem : {}", id);
        return paiementItemRepository.findById(id)
            .map(paiementItemMapper::toDto);
    }

    /**
     * Delete the paiementItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaiementItem : {}", id);

        paiementItemRepository.deleteById(id);
    }
}
